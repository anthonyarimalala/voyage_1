
package generalise;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





public class CrudOperation {

    private Connection connection;

    public CrudOperation(Connection connection) {
        this.connection = connection;
    }
    
    public <T> String saveReturn(T obj) {
        Class<?> clazz = obj.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesBuilder = new StringBuilder(" VALUES (");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);

                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                if (!columnAnnotation.autoIncrement()) {
                    queryBuilder.append(columnName).append(", ");
                    valuesBuilder.append("?, ");
                }
            }
        }

        queryBuilder.setLength(queryBuilder.length() - 2);
        valuesBuilder.setLength(valuesBuilder.length() - 2);

        queryBuilder.append(")").append(valuesBuilder).append(")");

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString(), Statement.RETURN_GENERATED_KEYS)) {
            int parameterIndex = 1;

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);

                    if (!columnAnnotation.autoIncrement()) {
                        field.setAccessible(true);
                        preparedStatement.setObject(parameterIndex++, field.get(obj));
                    }
                }
            }

            preparedStatement.executeUpdate();

            // Retrieve the generated ID
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return String.valueOf(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Failed to retrieve generated ID.");
            }
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // Handle error appropriately in your application
        }
    }

 
    public <T> void save(T obj) {
        Class<?> clazz = obj.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder valuesBuilder = new StringBuilder(" VALUES (");

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);

                
                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                if (!columnAnnotation.autoIncrement()) {
                    queryBuilder.append(columnName).append(", ");
                    valuesBuilder.append("?, ");
                }
                
            }
        }

        queryBuilder.setLength(queryBuilder.length() - 2);
        valuesBuilder.setLength(valuesBuilder.length() - 2);

        queryBuilder.append(")").append(valuesBuilder).append(")");

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);

                    if (!columnAnnotation.autoIncrement()) {
                        field.setAccessible(true);
                        preparedStatement.setObject(parameterIndex++, field.get(obj));
                    }
                }
            }

            preparedStatement.executeUpdate();
            System.out.println("Data saved successfully.");
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public <T> List<T> selectAll(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        String query = "SELECT * FROM " + tableName;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        field.setAccessible(true);
                        field.set(obj, resultSet.getObject(columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name()));
                    }
                }
                result.add(obj);
            }
        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public <T> List<T> selectAllById(Class<T> clazz, String columnName, Object id) {
        List<T> result = new ArrayList<>();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        String query = "SELECT * FROM " + tableName+" WHERE "+columnName+" = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        field.setAccessible(true);
                        field.set(obj, resultSet.getObject(columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name()));
                    }
                }
                result.add(obj);
            }
        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return result;
    }
    public <T> List<T> selectByQuery(Class<T> clazz, String query) {
        List<T> result = new ArrayList<>();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        field.setAccessible(true);
                        field.set(obj, resultSet.getObject(columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name()));
                    }
                }
                result.add(obj);
            }
        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return result;
    }

    public <T> T selectById(Class<T> clazz, Object id) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        String idColumnName = null;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);

                if (columnAnnotation.id()) {
                    idColumnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                    break;
                }
            }
        }

        String query = "SELECT * FROM " + tableName + " WHERE " + idColumnName + " = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        field.setAccessible(true);
                        field.set(obj, resultSet.getObject(columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name()));
                    }
                }
                return obj;
            }
        } catch (SQLException | ReflectiveOperationException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public <T> void update(T obj, Object id) {
        Class<?> clazz = obj.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        StringBuilder queryBuilder = new StringBuilder("UPDATE " + tableName + " SET ");
        Field[] fields = clazz.getDeclaredFields();
        
        String idColumnName = null;

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                if (!columnAnnotation.autoIncrement()) {
                    queryBuilder.append(columnName).append(" = ?, ");
                }
                if (columnAnnotation.id()) {
                    idColumnName = columnName;
                }
            }
        }

        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE "+idColumnName+" = ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);

                    if (!columnAnnotation.autoIncrement()) {
                        field.setAccessible(true);
                        preparedStatement.setObject(parameterIndex++, field.get(obj));
                    }
                }
            }

            preparedStatement.setObject(parameterIndex, id);

            preparedStatement.executeUpdate();
            System.out.println("Data updated successfully.");
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    public <T> void update(T obj, String column, Object id) {
        Class<?> clazz = obj.getClass();

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        StringBuilder queryBuilder = new StringBuilder("UPDATE " + tableName + " SET ");
        Field[] fields = clazz.getDeclaredFields();
        
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                if (!columnAnnotation.autoIncrement()) {
                    queryBuilder.append(columnName).append(" = ?, ");
                }
            }
        }

        queryBuilder.setLength(queryBuilder.length() - 2);
        queryBuilder.append(" WHERE "+column+" = ?");

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString())) {
            int parameterIndex = 1;

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column columnAnnotation = field.getAnnotation(Column.class);

                    if (!columnAnnotation.autoIncrement()) {
                        field.setAccessible(true);
                        preparedStatement.setObject(parameterIndex++, field.get(obj));
                    }
                }
            }

            preparedStatement.setObject(parameterIndex, id);

            preparedStatement.executeUpdate();
            System.out.println("Data updated successfully.");
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> void delete(Class<T> clazz, Object id) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        String idColumnName = null;

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);

                if (columnAnnotation.id()) {
                    idColumnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                    break;
                }
            }
        }

        String query = "DELETE FROM " + tableName + " WHERE " + idColumnName + " = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public <T> void deleteAll(Class<T> clazz) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        String query = "DELETE FROM " + tableName;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public <T> void delete(Class<T> clazz, String column, Object id) {
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException("Class must be annotated with @Table");
        }

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        String tableName = tableAnnotation.name();

        String query = "DELETE FROM " + tableName + " WHERE " + column + " = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Data deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public <T> void modifFromQuery(String query) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Data modified successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
