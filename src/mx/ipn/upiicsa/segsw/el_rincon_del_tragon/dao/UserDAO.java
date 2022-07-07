package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.exception.DAOInitializationException;
import mx.ipn.upiicsa.segsw.el_rincon_del_tragon.valueobject.UserValueObject;


/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */

public class UserDAO extends DataAccessObject{

	public UserDAO() throws ClassNotFoundException, SQLException {
		
		super();
	}
	/**
	 * 
	 * @param user
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public UserValueObject create(UserValueObject user) throws SQLException, DAOInitializationException
	{
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";
		
		System.out.println("UserDAO.create() - SQL - " + sql);
		System.out.println("UserDAO.create() - user - " + user);
		
		try
		{
			ps = prepareStatement(sql);

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			//ps.setInt(5, user.getDaysOfPasswordValidity());
			//ps.setBoolean(6, user.isTemporalPassword());
			//ps.setString(7, user.getActivationKey());
			//ps.setString(8, user.getStatus());
			
			ps.executeUpdate();
			
			return user;
		}
		finally
		{
			closeStatement(ps);
		}
	}
	/**
	 * 
	 * @param email
	 * @return
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public UserValueObject findById(String email) throws SQLException, DAOInitializationException
	{
		UserValueObject user = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		String sql = "SELECT * FROM users WHERE email = ?";
		System.out.println("UserDAO.authenticate() - SQL - " + sql);
		
		
		try
		{
			stmt = prepareStatement(sql);

			stmt.setString(1, email);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) // Encontro un registro -- Credenciales validas
			{
				user = new UserValueObject();
				
				user.setEmail(rs.getString("email"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				//user.setDaysOfPasswordValidity(rs.getInt("days_of_password_validity"));
				//user.setDateOfLastPasswordUpdate(new Date(rs.getDate("date_of_last_password_update").getTime()));
				//user.setTemporalPassword(rs.getBoolean("is_temporal_password"));
				//user.setActivationKey(rs.getString("activation_key"));
				//user.setStatus(rs.getString("status"));
				
				return user;
			}
			else
			{
				return null;
			}
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(stmt);
		}
	}
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return UserValueObject
	 * @throws SQLException
	 * @throws DAOInitializationException
	 */
	public UserValueObject authenticate(String email, String password) throws SQLException, DAOInitializationException
	{
		UserValueObject user = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		String sql = "SELECT * FROM users WHERE email = ?  AND password = ?";
		System.out.println("UserDAO.authenticate() - SQL - " + sql);
		
		
		try
		{
			ps = prepareStatement(sql);

			ps.setString(1, email); // se envia el email
			ps.setString(2, password); // se envia el password
			
			rs = ps.executeQuery(); // se ejecuta el query
			
			if(rs.next()) // Encontro un registro -- Credenciales validas
			{
				user = new UserValueObject();
				
				user.setEmail(rs.getString("email"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				//user.setDaysOfPasswordValidity(rs.getInt("days_of_password_validity"));
				//user.setDateOfLastPasswordUpdate(new Date(rs.getDate("date_of_last_password_update").getTime()));
				//user.setTemporalPassword(rs.getBoolean("is_temporal_password"));
				//user.setActivationKey(rs.getString("activation_key"));
				//user.setStatus(rs.getString("status"));
				
				return user;
			}
			else
			{
				// no se encontro al usuario
				return null;
			}
		}
		finally
		{
			closeResultSet(rs);
			closeStatement(ps);
		}
	}
}
