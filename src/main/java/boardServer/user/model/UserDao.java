package boardServer.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private UserDao() {
		setConnection();
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	private void setConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/board_server_db";
			String user = "root";
			String password = "zorkswl123!";

			this.conn = DriverManager.getConnection(url, user, password);

			System.out.println("db 연동 성공");
		} catch (Exception e) {
			System.out.println("db 연동 실패");
			e.printStackTrace();
		}

	}

	public List<UserResponseDto> findUserAll() {
		List<UserResponseDto> list = new ArrayList<UserResponseDto>();
		try {
			String sql = "SELECT id,email,name,birth,gender,country,telecom,phone,agree FROM users";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String email = rs.getString(2);
				String name = rs.getString(3);
				String birth = rs.getString(4);
				String gender = rs.getString(5);
				String country = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);
				boolean agree = rs.getBoolean(9);
				UserResponseDto user = new UserResponseDto(id, email, name, birth, gender, country, telecom, phone,
						agree);
				list.add(user);

			}

			System.out.println("조회 성공");

		} catch (Exception e) {
			System.out.println("조회 실패");
		}

		return list;
	}

	public UserResponseDto findUserByIdAndPassword(String id, String password) {
		UserResponseDto user = null;

		try {
			String sql = "SELECT id,email,name,birth,gender,country,telecom,phone,agree FROM users WHERE id = ? AND password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString(2);
				String name = rs.getString(3);
				String birth = rs.getString(4);
				String gender = rs.getString(5);
				String country = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);
				boolean agree = rs.getBoolean(9);
				user = new UserResponseDto(id, email, name, birth, gender, country, telecom, phone, agree);

			}

			System.out.println("조회 성공");

		} catch (Exception e) {
			System.out.println("조회 실패");
		}

		return user;
	}

	public UserResponseDto createUser(UserRequestDto userDto) {
		String sql = "INSERT INTO users (id,password,email,name,birth,gender,country,telecom,phone,agree) VALUES (?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPassword());
			pstmt.setString(3, userDto.getEmail().equals("") ? null : userDto.getEmail());
			pstmt.setString(4, userDto.getName());
			pstmt.setString(5, userDto.getBirth());
			pstmt.setString(6, userDto.getGender());
			pstmt.setString(7, userDto.getCountry());
			pstmt.setString(8, userDto.getTelecom());
			pstmt.setString(9, userDto.getPhone());
			pstmt.setBoolean(10, userDto.isAgree());

			pstmt.execute();

			return findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public UserResponseDto updateUserPassword(UserRequestDto userDto, String newPassword) {
		UserResponseDto user = null;

		if (newPassword == null || newPassword.equals("")) {
			return user;
		}

		String sql = "UPDATE users SET password = ? WHERE id = ? AND password = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userDto.getId());
			pstmt.setString(3, userDto.getPassword());

			pstmt.execute();
			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);

			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	public UserResponseDto updateUser(UserRequestDto userDto, String newPassword) {
		UserResponseDto user = null;
		user = findUserByIdAndPassword(userDto.getId(), userDto.getPassword());
		
		if(user == null) { 			
			return null;
		}
		
		String sql = "UPDATE users SET password = ?, name = ? ,birth = ? , telecom = ? ,gender = ? , country = ? , agree = ? WHERE id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, userDto.getName());
			pstmt.setString(3, userDto.getBirth());
			pstmt.setString(4, userDto.getTelecom());
			pstmt.setString(5, userDto.getGender());
			pstmt.setString(6, userDto.getCountry());
			pstmt.setBoolean(7, userDto.isAgree());
			pstmt.setString(8, userDto.getId());
			

			pstmt.execute();
			User userVo = findUserById(userDto.getId());
			user = new UserResponseDto(userVo);

			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private User findUserById(String id) {
		User user = null;

		try {
			String sql = "SELECT id,email,name,birth,gender,country,telecom,phone,agree,reg_date,mod_date FROM users WHERE id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String email = rs.getString(2);
				String name = rs.getString(3);
				String birth = rs.getString(4);
				String gender = rs.getString(5);
				String country = rs.getString(6);
				String telecom = rs.getString(7);
				String phone = rs.getString(8);
				boolean agree = rs.getBoolean(9);
				Timestamp regDate = rs.getTimestamp(10);
				Timestamp modDate = rs.getTimestamp(11);
				user = new User(id, email, name, birth, gender, country, telecom, phone, agree, regDate, modDate);

			}

			System.out.println("조회 성공");

		} catch (Exception e) {
			System.out.println("조회 실패");
		}

		return user;
	}


	public boolean deleteUser(UserRequestDto userDto) {

		String sql = "DELETE FROM users WHERE id = ? AND password = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userDto.getId());
			pstmt.setString(2, userDto.getPassword());
			
			pstmt.execute();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

}
