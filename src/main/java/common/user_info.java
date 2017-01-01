package common;

import java.util.Date;

/**
 * Created by dongho on 1/1/17.
 */
public class user_info
{
    private int         no;
    private String      email;
    private String      pwd;
    private String      name;

    private Date        createdDate;
    private Date        modifiedDate;

    public int getNo()
    {
        return no;
    }

    public user_info setNo(int no)
    {
        this.no = no;
        return this;
    }

    public String getEmail()
    {
        return email;
    }

    public user_info setEmail(String email)
    {
        this.email = email;
        return this;
    }

    public String getPassword()
    {
        return pwd;
    }

    public user_info setPassword(String pwd)
    {
        this.pwd = pwd;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public user_info setName(String name)
    {
        this.name = name;
        return this;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public user_info setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
        return this;
    }

    public Date getModifiedDate()
    {
        return modifiedDate;
    }

    public user_info setModifiedDate(Date modifiedDate)
    {
        this.modifiedDate = modifiedDate;
        return this;
    }
}
