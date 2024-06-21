/*
----------------------------------------------------------------------------------------------------------
    Name:		Movie
    Developers:	Mitchell Jones, Ollie Peel, Alexander Brinkly, Rawan Alhachami, Jeffrey Saylor
    Language:	Java
    Date:		06-20-2024
    Purpose:    Used to create Movie objects in the ManagmentSysem
----------------------------------------------------------------------------------------------------------
    Change Log
----------------------------------------------------------------------------------------------------------
    Who		Date		Change
    MTJ		06-20-2024	Original Version of Code.
----------------------------------------------------------------------------------------------------------
*/
import java.util.Date;

public class Movie {
    private String Title;
    private String Description; 
    private Date ReleaseDate;
    private Date ReceiveDate; 
    private Status ReleaseStatus;
    
    // Enum for movie status
    enum Status {
        RECEIVED, RELEASED
    }

    // Constructor
    public Movie(String title, String description, Date releaseDate, Date receiveDate, Status status) {
        this.Title = title;
        this.Description = description;
        this.ReleaseDate = releaseDate;
        this.ReceiveDate = receiveDate;
        this.ReleaseStatus = status;
    }

    // Setters
    public void setTitle(String title) {
        this.Title = title;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setReleaseDate(Date releaseDate) {
        this.ReleaseDate = releaseDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.ReceiveDate = receiveDate;
    }

    public void setReleaseStatus(Status releaseStatus) {
        this.ReleaseStatus = releaseStatus;
    }

    // Getters
    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public Date getReceiveDate() {
        return ReceiveDate;
    }

    public Status getReleaseStatus() {
        return ReleaseStatus;
    }

    
    @Override // Returns all of the movies attributes in an organized box
    public String toString(){
        return  
                "\nTitle: " + Title +
                "\nDescription: " + Description +  
                "\nReleaseDate: " + ReleaseDate +
                "\nReceiveDate: " + ReceiveDate +
                "\nReleaseStatus: " + ReleaseStatus +
                "\n____________________________________" ;
    }

}
