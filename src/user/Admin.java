package user;

/**
 * @author Tobias Heidlund
 */
@SuppressWarnings("unused")
public class Admin extends Guest{
    public final boolean createPerm = true;
    public final boolean updatePerm = true;
    public final boolean deletePerm = true;
}
