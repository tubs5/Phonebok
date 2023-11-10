package userInterface.displayables.admin;

import user.Admin;
import userInterface.Activities;
import userInterface.Requestor;
import userInterface.displayables.Displayable;
import userInterface.events.SwitchEvent;

import static userInterface.Activities.EXTRAS_USER;

/**
 * @author Tobias Heidlund
 */
public class Login extends Displayable {

    public Login(Requestor requestor) {
        super(requestor);
    }

    @Override
    public void Display() {
        requestor.print("Admin Login:");
    }

    @Override
    public SwitchEvent Interact() {
        String username = requestor.getString("Username:");
        String password = requestor.getString("Password:");

        if(username.equals("admin") && password.equals("admin")){
            requestor.print("Login Successful");
            return new SwitchEvent.SwitchEventBuilder(Activities.ADMIN_MAIN_MENU).addExtra(EXTRAS_USER,new Admin()).build();
        }else{
            requestor.print("Login Failed");
            return new SwitchEvent.SwitchEventBuilder(Activities.MAIN_MENU).build();
        }

    }
}
