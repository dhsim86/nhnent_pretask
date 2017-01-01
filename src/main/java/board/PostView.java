package board;

import java.sql.*;
import common.post_info;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dongho on 1/1/17.
 */
public class PostView
{
    public static post_info getPost(Connection conn, int post_number)
        throws SQLException
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        post_info post = new post_info();

        try
        {
            stmt = conn.prepareStatement(
                    "select users.name, board.no, board.mod_date, board.title, board.contents" +
                    " from users inner join board" +
                    " on users.no = board.user_no and board.no = ?");
            stmt.setInt(1, post_number);
            rs = stmt.executeQuery();

            if (rs.next())
            {
                post.setUserName(rs.getString("users.name"))
                    .setNo(rs.getInt("board.no"))
                    .setTitle(rs.getString("board.title"))
                    .setContents(rs.getString("board.contents"))
                    .setModifiedDate(rs.getDate("board.mod_date"));
            }
            else
            {
                throw new SQLException();
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
            }
            catch (Exception e)
            {

            }
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            }
            catch (Exception e)
            {

            }

            return post;
        }
    }
}
