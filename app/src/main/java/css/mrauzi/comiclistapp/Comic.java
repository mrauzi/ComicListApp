package css.mrauzi.comiclistapp;

/**
 * Comic - this class creates a comic with an id, name, price, and volume and the methods to set
 * and access the data.
 *
 * Created by mrauzi on 4/7/2017.
 */

public class Comic {

    private Long _id;           // database record ID
    private String name;        // comic name
    private Double price;       // comic price
    private Integer volume;     // comic volume number

    /**
     * Comic() - constructor to create a comic object
     *
     * @param _id the id of the comic in the database
     * @param name the name of the comic
     * @param price the price of the comic
     * @param volume the volume number of the comic
     */
    public Comic(Long _id, String name, Double price, Integer volume) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.volume = volume;
    }

    /**
     * get_id() - returns the id of the comic
     *
     * @return the id of the comic
     */
    public Long get_id() {
        return _id;
    }

    /**
     * getName() - returns the name of the comic
     *
     * @return the name of the comic
     */
    public String getName() {
        return name;
    }

    /**
     * setName() - sets the name of the comic
     *
     * @param name the String variable of the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getPrice() - returns the price of the comic
     *
     * @return the price of the comic
     */
    public Double getPrice() {
        return price;
    }

    /**
     * setPrice() - sets the price of the comic
     *
     * @param price the double variable of the price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * getVolume() - returns the volume number of the comic
     *
     * @return the volume of the comic
     */
    public Integer getVolume() {
        return volume;
    }

    /**
     * setVolume() - sets the volume number of the comic
     *
     * @param volume the int variable of the volume
     */
    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    /**
     * toString() - the String description of the comic
     *
     * @return the description of the comic
     */
    @Override
    public String toString() {
        return "Comic{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                '}';
    }
}
