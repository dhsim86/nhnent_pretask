package common;

import java.util.Date;

/**
 * Created by dongho on 1/1/17.
 */
public class post_info
{
    private int     no;
    private Date    modifiedDate;
    private String  user_name;
    private String  contents;
    private String  title;

    public int getNo()
    {
        return no;
    }

    public post_info setNo(int no)
    {
        this.no = no;
        return this;
    }

    public Date getModifiedDate()
    {
        return modifiedDate;
    }

    public post_info setModifiedDate(Date modifiedDate)
    {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getUserName()
    {
        return user_name;
    }

    public post_info setUserName(String user_name)
    {
        this.user_name = user_name;
        return this;
    }

    public String getContents()
    {
        return contents;
    }

    public post_info setContents(String contents)
    {
        this.contents = contents;
        return this;
    }

    public String getTitle()
    {
        return title;
    }

    public post_info setTitle(String title)
    {
        this.title = title;
        return this;
    }
}
