/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Classes.User;
import Classes.Validation;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Kary Johnson
 */
public class DatabaseController extends HttpServlet  {
    private final String DB_URL;
    private final String DB_USERNAME;
    private final String DB_PASSWORD;
    private Connection connection;
    
    public DatabaseController(){
        DB_URL      = "jdbc:mysql://127.0.0.1:3306/poster_bomb_db";
        DB_USERNAME = "kary_johnson";
        DB_PASSWORD = "password";        
    }
    
    public void connectToDatabase(){
        try{
            Class.forName("com.mysql.jdbc.Driver"); // driver loader
            connection = (Connection) DriverManager.getConnection 
            (DB_URL, DB_USERNAME, DB_PASSWORD); // connection to database           
        }
        catch(ClassNotFoundException | SQLException ex){
            log("SQL Error: " + ex.getMessage());
        }             
    }
    private void loadColumnData(PreparedStatement statement, String[] columns)throws SQLException{
        for(int i = 0; i < columns.length; i++){
            statement.setString(i+1, columns[i]); // Load column data into prepared statement object
        }  
    }
    private void executeQuery(String query, String[] columns)throws SQLException{ // Passes in a query and inserts data into database
        PreparedStatement statement = connection.prepareStatement(query); // Creates statement with connection to database
        loadColumnData(statement, columns);     
        statement.executeUpdate();// execute query
    }
    
    private ResultSet getData(String query, String[] columns) throws SQLException{ // Used to retrieve data from the database and return it     
      PreparedStatement statement = connection.prepareStatement(query);  // creates statement for database query
      if(columns != null){
        loadColumnData(statement, columns); 
      }      
      return statement.executeQuery(); // Returns retrieved data from database.
    }
    
    public void deleteUserInfo(String userId)throws SQLException{
        String query = "DELETE FROM users WHERE user_id = ?";
        executeQuery(query, new String[]{userId});
        
    }
    public void updateUserInfo(String info, String column, String userId, String table ) throws SQLException{
        String query = "UPDATE " + table + " SET " + column + " = ? WHERE user_id = ?";
        executeQuery(query, new String[]{info, userId});
    }
    
    public ArrayList<Map> getUsers() throws SQLException{
        ArrayList<Map> userList = new ArrayList();        
        String query = "SELECT * FROM user_profile INNER JOIN users "
                        + "ON user_profile.user_id = users.user_id "
                        + "WHERE users.account_type IS NULL";   
        ResultSet result = getData(query, null);
        
        while(result.next()){
            Map<String, String> userMap = new HashMap();
            userMap.put("userId", result.getString("user_id"));
            userMap.put("firstname",result.getString("firstname"));
            userMap.put("lastname",result.getString("lastname"));
            userMap.put("username",result.getString("username"));
            userMap.put("email", result.getString("email"));
            userList.add(userMap);
        }
        return userList;
    }
    public boolean isAdmin(String userId)throws SQLException{
        String query = "SELECT account_type FROM users WHERE user_id = ?";
        ResultSet result = getData(query, new String[]{userId});
        
        while(result.next()){
            if(result.getString("account_type") != null){
                return true;
            }
        }
        return false;
    }
    
    public User getUserInfo(String userId)throws SQLException{ // Used to extract user profile information from database
        String query = "SELECT * FROM user_profile INNER JOIN users "
                + "ON user_profile.user_id = users.user_id WHERE users.user_id = ?";
        ResultSet result = getData(query, new String[]{userId});
        User user = new User();
        while(result.next()){
            user.setUserId(result.getString("user_id"));
            user.setUsername(result.getString("username"));
            user .setFirstname(result.getString("firstname"));
            user.setLastname(result.getString("lastname"));
            user.setEmail(result.getString("email"));            
        }
        return user;
    }
    public void postComment(String userId, String postId, String comment)throws SQLException{ // used to insert user comments 
        String query = "INSERT INTO comments (user_id, post_id, comment) "
                + "VALUES(?,?,?)";
        executeQuery(query, new String[]{userId, postId, comment});     
    }
    
    public ArrayList<Map> getComments(String postId)throws SQLException{ // gets comments corresponding to specific post
        ArrayList<Map> comments = new ArrayList();        
        ResultSet result;
        String query = "SELECT username, comment FROM comments "
                + "INNER JOIN users "
                + "ON comments.user_id = users.user_id WHERE comments.post_id = ?"
                + "ORDER BY comments.comment_id DESC";        
        result = getData(query, new String[]{postId});
        while(result.next()){
            Map<String,String> data = new HashMap();
            data.put("username", result.getString("username"));
            data.put("comment", result.getString("comment"));
            comments.add(data);
        }
        return comments;
    }
    
    public ArrayList<Map> getPosts(String postId)throws SQLException{ // Gets all posts or gets one post
        String query;
        ArrayList<Map> posts = new ArrayList<>();
        ResultSet result;
        if(postId != null){ // if post id is specified, retrieve post information corresponding to that post id
            query = "SELECT post_id, username, post FROM post "
                    + "INNER JOIN users "
                    + "ON post.user_id = users.user_id WHERE post_id = ?";              
            result = getData(query, new String[]{postId});
        }
        else{ // else rerieve all post infromation
            query = "SELECT post_id, username, post FROM post "
                + "INNER JOIN users "
                + "ON post.user_id = users.user_id"
                + " ORDER BY post_id DESC ";
            result = getData(query, null);
        }
        
        while(result.next()){
            Map<String, String> userAndPost = new HashMap<>();
            userAndPost.put("postId", result.getString("post_id"));
            userAndPost.put("username", result.getString("username"));
            userAndPost.put("post", result.getString("post"));
            posts.add(userAndPost);
        }
        return posts;
    }
    
    public void uploadPost(String userId, String post)throws SQLException{
        String query = "INSERT INTO post(user_id, post) "
                + "VALUES(?,?)";
        executeQuery(query, new String[]{userId, post});        
    }
    
    public String userExist(User user)throws SQLException{ // Used to check for existing account
        String query = "SELECT * FROM user_profile INNER JOIN users "
                + "ON user_profile.user_id = users.user_id";
        ResultSet result = getData(query, null);
        Validation valid = new Validation();
        String message = "";
        
        while(result.next()){
            if(valid.inputMatch(result.getString("email"), user.getEmail())){
               message += "The email address entered is already in use.";
               break;
            }
            if(valid.inputMatch(result.getString("username"), user.getUsername())){
               message += "\nThe username entered is taken.";
               break;
            }
        }       
        return message;
    }
    
    public String verifyLoginInfo(User user) throws SQLException, NoSuchAlgorithmException{ // Used to verify user login info
        String userId, username, password;
        String query = "SELECT * FROM users";
        Validation validate = new Validation();
        ResultSet result =  getData(query,null);
        
        while(result.next()){
            userId   = result.getString("user_id");
            username = result.getString("username");
            password = result.getString("password");
            if(validate.inputMatch(username, user.getUsername()) 
                    && validate.inputMatch(password, validate.hashPassword(user.getPassword()))){
                return userId;
            }            
        }
        return null;
    }
    public void createNewUser(User user) throws SQLException{ // Creates new user with data passed in by a user object
        String query = "INSERT INTO users(username, password) VALUES(?,?) "; // Query fore inserting new user data
        executeQuery(query, new String[]{user.getUsername(),user.getPassword()});// Inserts data to database
        createNewProfile(user); // Create new user profile
            
    }
    public void createNewProfile(User user)throws SQLException{ // used to insert user profile data
        String query = "SELECT MAX(user_id) as user_id FROM users;"; // Query used to select the last user id created
        ResultSet result = getData(query,null); // Stores data retrieved from database query
        String lastId = "";
        
        while(result.next()){ // Iterates each line of the result set
            lastId = result.getString("user_id");    
        }      
        query = "INSERT INTO user_profile(user_id, firstname, lastname, email) "
                + "VALUES(?,?,?,?)";
        executeQuery(query, new String[]{lastId, user.getFirstname(),user.getLastname(),user.getEmail()});
    }
    public void closeConn() throws SQLException{ // Used to close connection to the database
        connection.close();
    }
}
