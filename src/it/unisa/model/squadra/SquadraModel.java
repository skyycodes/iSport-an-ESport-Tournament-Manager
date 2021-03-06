package it.unisa.model.squadra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.unisa.model.ModelInterface;
import it.unisa.model.connessione.DriverManagerConnectionPool;
import it.unisa.model.giocatore.GiocatoreBean;
import it.unisa.model.gioco.GiocoBean;

public class SquadraModel implements ModelInterface<SquadraBean, String>{

	@Override
	public SquadraBean doRetriveByKey(String nome) throws SQLException {
		PreparedStatement statement = null;

		SquadraBean bean = new SquadraBean();
		String sql = "SELECT * FROM squadra WHERE nome=?";

		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, nome);
			System.out.println("DoRetriveByKey=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bean.setNome(rs.getString("nome"));
				bean.setNazionalita(rs.getString("nazionalita"));
				bean.setTeamImage(rs.getString("imgSquadra"));
				bean.setProprietario(rs.getString("proprietario"));
				System.out.println("Ho trovato la squadra"+nome);
			}
		}
		if(!bean.isEmpty()) {
			System.out.println("ho trovato una squadra con questo nome nel db");
			return bean;
		}
			
		else {
			System.out.println("Non ho trovato la squadra");
			return null;
		}
			
	}

	public Collection<SquadraBean> doRetriveByUser(String email) {
		PreparedStatement statement = null;

		String sql = "select * from utente,squadra where squadra.proprietario=?";

		ArrayList<SquadraBean> collection= new ArrayList<SquadraBean>();
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			System.out.println("DoRetriveByUser=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SquadraBean bean = new SquadraBean();
				bean.setNome(rs.getString("nome"));
				bean.setNazionalita(rs.getString("nazionalita"));
				bean.setTeamImage(rs.getString("imgSquadra"));
				bean.setProprietario(rs.getString("proprietario"));

				collection.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collection;
	}
	
	public Collection<SquadraBean> doRetriveByUserNum(String email,int num) {
		PreparedStatement statement = null;

		String sql = "select * from utente,squadra where squadra.proprietario=?and(select count(*) from giocatore as g where g.nomesquadra=squadra.nome)=?";

		ArrayList<SquadraBean> collection= new ArrayList<SquadraBean>();
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, email);
			statement.setInt(2, num);
			System.out.println("DoRetriveByUserNum=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SquadraBean bean = new SquadraBean();
				bean.setNome(rs.getString("nome"));
				bean.setNazionalita(rs.getString("nazionalita"));
				bean.setTeamImage(rs.getString("imgSquadra"));
				bean.setProprietario(rs.getString("proprietario"));

				collection.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return collection;
	}
	
	
	public Collection<SquadraBean> doRetriveAllNum(int num) throws SQLException {
		PreparedStatement statement = null;

		String sql = "SELECT * FROM squadra where (select count(*) from giocatore as g where g.nomesquadra=squadra.nome)=?";

		ArrayList<SquadraBean> collection= new ArrayList<SquadraBean>();
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			System.out.println("DoRetriveAllNum=" + statement.toString());
			statement.setInt(1, num);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SquadraBean bean = new SquadraBean();
				bean.setNome(rs.getString("nome"));
				bean.setNazionalita(rs.getString("nazionalita"));
				bean.setTeamImage(rs.getString("imgSquadra"));
				bean.setProprietario(rs.getString("proprietario"));
				collection.add(bean);
			}
		}
		return collection;
	}
	
	@Override
	public Collection<SquadraBean> doRetriveAll(String order) throws SQLException {
		PreparedStatement statement = null;

		String sql = "SELECT * FROM squadra";

		ArrayList<SquadraBean> collection= new ArrayList<SquadraBean>();
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			System.out.println("DoRetriveAll=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				SquadraBean bean = new SquadraBean();
				bean.setNome(rs.getString("nome"));
				bean.setNazionalita(rs.getString("nazionalita"));
				bean.setTeamImage(rs.getString("imgSquadra"));
				bean.setProprietario(rs.getString("proprietario"));
				collection.add(bean);
			}
		}
		return collection;
	}
	
	public Collection<GiocatoreBean> doRetrivePlayerFromSquadra(String name) throws SQLException {
		PreparedStatement statement = null;

		String sql = "SELECT * FROM giocatore where giocatore.nomesquadra=?";

		ArrayList<GiocatoreBean> collection= new ArrayList<GiocatoreBean>();
		System.out.println("Ciao"+name);
		
		try (Connection con = DriverManagerConnectionPool.getConnection()) {
			statement = con.prepareStatement(sql);
			statement.setString(1, name);
			System.out.println("doRetrivePlayerFromSquadra=" + statement.toString());
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				GiocatoreBean bean = new GiocatoreBean();
				bean.setNickname(rs.getString("nickname"));
				bean.setCognome(rs.getString("cognome"));
				bean.setDatanascita(rs.getString("dataN"));
				bean.setNome(rs.getString("nome"));
				bean.setNomesquadra(rs.getString("nomesquadra"));
				bean.setRuolo(rs.getString("ruolo"));
				bean.setPlayerImage(rs.getString("playerImage"));
				collection.add(bean);
				System.out.println(rs.getString("nickname"));
			}
		}
		return collection;
	}

	@Override
	public void doSave(SquadraBean squadra) throws SQLException {
		System.out.println("-----------SALVO LA SQUADRA-------------------");
		PreparedStatement statement = null;
		String sql = "INSERT INTO squadra values (?,?,?,?)";
		try(Connection con = DriverManagerConnectionPool.getConnection()){
		
			statement=con.prepareStatement(sql);
			statement.setString(1,squadra.getNome());
			statement.setString(2,squadra.getNazionalita());
			statement.setString(3,squadra.getProprietario());
			statement.setString(4,squadra.getTeamImage());
			System.out.println("doSave="+statement);
			statement.executeUpdate();
			con.commit();//<----- a volte vorrei non essere cos� tanto forte
		}
	}

	@Override
	public void doUpdate(SquadraBean product) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(String nome) throws SQLException {
		PreparedStatement statement	 = null;
		String sql = "DELETE FROM squadra WHERE (nome=?)";
		try(Connection con = DriverManagerConnectionPool.getConnection()){
		
			statement=con.prepareStatement(sql);
			statement.setString(1,nome);
			
			System.out.println("doDelete="+statement);
			statement.executeUpdate();	
			con.commit();//<----- a volte vorrei non essere cos� tanto forte
		}
	}

	
	
}
