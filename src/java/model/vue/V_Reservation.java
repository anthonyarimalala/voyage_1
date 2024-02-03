package model.vue;

import database.Connex;
import generalise.Column;
import generalise.CrudOperation;
import generalise.Table;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Table(name="v_reservation")
public class V_Reservation {
    
    @Column(name="id_reservation")
    int idReservation;
    
    @Column(name="id_client")
    int idClient;
    
    @Column(name="date_reservation")
    Date dateReservation;
    
    @Column(name="id_voyage")
    int idVoyage;
    
    @Column(name="quantite")
    double quantite;
    
    @Column(name="etat")
    int etat;
    
    @Column(name="voyage")
    String voyage;
    
    @Column(name="nom")
    String nom;
    
    @Column(name="prenom")
    String prenom;
    
    @Column(name="genre")
    int genre;
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Connection connection = Connex.getConnection();
        CrudOperation crud = new CrudOperation(connection);
        
        
    }
    
    public String getEtatReservation() {
        String etat = "";
        if(this.etat==0){
            etat = "Annulé";
        }else if(this.etat==5){
            etat = "En attente";
        }else if(this.etat==10){
            etat = "Validé";
        }
        return etat;
    }
    
    public String getCssEtatReservation() {
        String css = "";
        if(this.etat==0){
            css = "danger";
        }else if(this.etat==5){
            css = "primary";
        }else if(this.etat==10){
            css = "success";
        }
        return css;
    }
    
    public static List<V_Reservation> selectAllReservation(Connection connection) throws SQLException {
        List<V_Reservation> reservations = new ArrayList<>();
        
        // Requête SQL pour sélectionner toutes les réservations
        String query = "select * from v_reservation\n" +
                        "ORDER BY CASE etat\n" +
                        "    WHEN 5 THEN 1\n" +
                        "    WHEN 10 THEN 2\n" +
                        "    WHEN 0 THEN 3\n" +
                        "END, date_reservation DESC";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    V_Reservation reservation = mapFromResultSet(resultSet);
                    reservations.add(reservation);
                }
            }
        }
        
        return reservations;
    }
    public static List<V_Reservation> selectAllReservationByIdVoyage(Connection connection, int idVoyage) throws SQLException {
        List<V_Reservation> reservations = new ArrayList<>();
        
        // Requête SQL pour sélectionner toutes les réservations
        String query = "select * from v_reservation\n" +
                        "where id_voyage=?\n" +
                        "ORDER BY CASE etat\n" +
                        "    WHEN 5 THEN 1\n" +
                        "    WHEN 10 THEN 2\n" +
                        "    WHEN 0 THEN 3\n" +
                        "END, date_reservation DESC";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idVoyage);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    V_Reservation reservation = mapFromResultSet(resultSet);
                    reservations.add(reservation);
                }
            }
        }
        
        return reservations;
    }
    public static List<V_Reservation> selectAllReservationValidate(Connection connection) throws SQLException {
        List<V_Reservation> reservations = new ArrayList<>();
        
        // Requête SQL pour sélectionner toutes les réservations
        String query = "SELECT * FROM v_reservation WHERE etat=10 ORDER BY date_reservation DESC";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    V_Reservation reservation = mapFromResultSet(resultSet);
                    reservations.add(reservation);
                }
            }
        }
        
        return reservations;
    }
    
    public static List<V_Reservation> selectAllReservationDismissed(Connection connection) throws SQLException {
        List<V_Reservation> reservations = new ArrayList<>();
        
        // Requête SQL pour sélectionner toutes les réservations
        String query = "SELECT * FROM v_reservation WHERE etat=0 ORDER BY date_reservation DESC";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    V_Reservation reservation = mapFromResultSet(resultSet);
                    reservations.add(reservation);
                }
            }
        }
        
        return reservations;
    }
    
    public static List<V_Reservation> selectAllReservationWait(Connection connection) throws SQLException {
        List<V_Reservation> reservations = new ArrayList<>();
        
        // Requête SQL pour sélectionner toutes les réservations
        String query = "SELECT * FROM v_reservation WHERE etat=5 ORDER BY date_reservation DESC";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    V_Reservation reservation = mapFromResultSet(resultSet);
                    reservations.add(reservation);
                }
            }
        }
        
        return reservations;
    }
    
    private static V_Reservation mapFromResultSet(ResultSet resultSet) throws SQLException {
        V_Reservation reservation = new V_Reservation();
        reservation.setIdReservation(resultSet.getInt("id_reservation"));
        reservation.setIdClient(resultSet.getInt("id_client"));
        reservation.setDateReservation(resultSet.getDate("date_reservation"));
        reservation.setIdVoyage(resultSet.getInt("id_voyage"));
        reservation.setQuantite(resultSet.getDouble("quantite"));
        reservation.setEtat(resultSet.getInt("etat"));
        reservation.setVoyage(resultSet.getString("voyage"));
        reservation.setNom(resultSet.getString("nom"));
        reservation.setPrenom(resultSet.getString("prenom"));
        reservation.setGenre(resultSet.getInt("genre"));
        return reservation;
    }

    public V_Reservation() {
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(int idVoyage) {
        this.idVoyage = idVoyage;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
    
    
}
