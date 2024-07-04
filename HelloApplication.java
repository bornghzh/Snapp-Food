package com.example.project;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;



public class HelloApplication extends Application {
    static String search , address,order , string , string2 , name , pass , whatToLoad="First_login_register.fxml";
    static boolean exist2,isManager,isUser,foodSelected=false, restaurantSelected = false , appRunning = true, exist = false, adminLoggedIn = false, userLoggedIn = false;
    static int resId=0,numFoodType, n=0 ,m=0, thisOrder=0 ,commentId=0 , thisFood =0 , thisAdmin = 0, thisUser = 0 , restaurantId=0 , orderId=0 , foodId=0 , thisRestaurant=0  , number=0 , number2;
    static int restaurantNumber;
    static double doub;
    static ArrayList<Admin> admins = new ArrayList<>();
    static ArrayList<Restaurant> foundRestaurants= new ArrayList<>();
    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Order> orders = new ArrayList<>();

    public void test(){
        Admin aaa = new Admin("ali","1234");
        Restaurant rrr = new Restaurant(0,"res");
        aaa.restaurants.add(rrr);
        Admin bbb = new Admin("abbas","1234");
        Restaurant rr = new Restaurant(0,"res");
        bbb.restaurants.add(rr);
        admins.add(aaa);
        admins.add(bbb);
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("First_login_register.fxml"));

        //test();

        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Snapp!");
        stage.setScene(scene);
        stage.show();
    }

    Stage stage;
    Scene scene;
    Parent root;

    /////////////////////////////////////////manager signup functions
    public void managerSignUp(ActionEvent e) throws IOException{
        whatToLoad = new String("Admin_signup1.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void adminSignUpGoBackTo1(ActionEvent e) throws IOException{
        admins.remove(admins.size()-1);

        whatToLoad = new String("Admin_signup1.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField adminSignUpName;
    @FXML
    TextField adminSignUpPass;
    @FXML
    TextField adminSignUpResNum;
    @FXML
    Label errorLabel2;
    public void adminSignUpGoTo2(ActionEvent e) throws IOException{
        name = adminSignUpName.getText();
        pass = adminSignUpPass.getText();
        restaurantNumber=Integer.parseInt(adminSignUpResNum.getText());
        exist = false;
        for (int i = 0; i < admins.size(); i++)
            if (name.equals(admins.get(i).name)) exist = true;
        for (int i = 0; i < users.size(); i++)
            if (name.equals(users.get(i).name)) exist = true;

        if (exist) {
            errorLabel2.setText("name already exists, please try another name!!");
        }

        if(!exist) {
            Admin newAdmin = new Admin(name,pass);
            admins.add(newAdmin);

            whatToLoad = new String("Admin_signup2.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    TextField adminSignUpResName;
    @FXML
    TextField adminSignUpAddress;
    public void adminSignUpGoTo3(ActionEvent e) throws IOException{
        if(restaurantNumber>n){
            Restaurant res=new Restaurant(resId,adminSignUpResName.getText());
            res.location = adminSignUpAddress.getText().intern();
            admins.get(admins.size()-1).restaurants.add(res);

            resId++;

            whatToLoad = new String("Admin_signup2.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            n++;
        }
        else{
            whatToLoad = new String("Restaurant_admin.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    TextField adminSignUpFoodName;
    @FXML
    TextField adminSignUpFoodPrice;
    @FXML
    TextField adminSignUpFoodType;

    public void adminSignUpGoTo4(ActionEvent e) throws IOException{
            n=0;
            thisAdmin = admins.size()-1;

            whatToLoad = new String("Restaurant_admin.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////user signup functions
    public void userSignUp(ActionEvent e) throws IOException{
        whatToLoad = new String("customer_signup.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    TextField textPass;
    @FXML
    TextField textName;
    @FXML
    TextField textAddress;
    @FXML
    Label errorLabel;
    public void signedUp(ActionEvent e) throws IOException{
        name = textName.getText();
        pass = textPass.getText();
        address = textAddress.getText();

        exist = false;
        for (int i = 0; i < users.size(); i++)
            if (name.equals(users.get(i).name)) exist = true;
        for (int i = 0; i < admins.size(); i++)
            if (name.equals(admins.get(i).name)) exist = true;


        if (exist) {
            errorLabel.setText("name already exists, please try another name!!");
            /*whatToLoad = new String("First_login_register.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            root.getChildrenUnmodifiable().add(errorLabel);
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/
        }
        if (!exist) {
            User newUser = new User(name, pass, address);
            users.add(newUser);
            thisUser=users.size()-1;

            whatToLoad = new String("first_custmor_panel.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////manager sign in functions
    public void managerSignIn(ActionEvent e) throws IOException{
        whatToLoad = new String("Admin_signin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField managerSignInName;
    @FXML
    TextField managerSignInResName;
    @FXML
    TextField managerSignInPass;
    @FXML
    Label errorLabel3;
    public void managerSignedIn(ActionEvent e) throws IOException {
        name = managerSignInName.getText();
        pass = managerSignInPass.getText();
        address = managerSignInResName.getText();

        exist = false;
        exist2=false;
        for (int i = 0; i < admins.size(); i++)
            if (name.equals(admins.get(i).name)) {
                exist = true;
                if (!pass.equals(admins.get(i).password)) exist = false;
                if (pass.equals(admins.get(i).password)) thisAdmin = i;
                for (int j = 0; j < admins.get(i).restaurants.size(); j++) {
                    if (admins.get(i).restaurants.get(j).name.equals(address)) {thisRestaurant = j; exist2=true;}
                }
                break;
            }

        if (!exist || !exist2) {
            errorLabel3.setText("username or password or restaurant name wrong");

            /*whatToLoad = new String("Admin_signin.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/
        }

        if (exist && exist2) {
            whatToLoad = new String("Restaurant_admin.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void managerForgetPass(ActionEvent e) throws IOException{
        whatToLoad = new String("forgot_password.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////user sign in functions
    public void userSignIn(ActionEvent e) throws IOException{
        whatToLoad = new String("Customer_signin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField userSignInName;
    @FXML
    TextField userSignInPass;
    @FXML
    Label errorLabel4;
    public void userSignedIn(ActionEvent e) throws IOException{
        name = userSignInName.getText();
        pass = userSignInPass.getText();

        exist = false;
        for (int i = 0; i < users.size(); i++)
            if (name.equals(users.get(i).name)){
                exist = true;
                if(!pass.equals(users.get(i).password)) exist=false;
                if(pass.equals(users.get(i).password)) thisUser=i;
            }

        if (!exist) {
            errorLabel4.setText("username or password wrong");
            /*whatToLoad = new String("Customer_signin.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/
        }

        if(exist) {
            whatToLoad = new String("first_custmor_panel.fxml");
            root = FXMLLoader.load(getClass().getResource(whatToLoad));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void userForgetPass(ActionEvent e) throws IOException{
        whatToLoad = new String("forgot_password.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////restaurant management functions
    public void resManagementMenu(ActionEvent e) throws IOException{
        whatToLoad = new String("Menu_admin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Label resManagementName;
    @FXML
    Label resManagementAddress;
    @FXML
    Label resManagementFoodTypes;
    public void resManagementDetails(ActionEvent e) throws IOException{
        resManagementName.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).name);
        resManagementAddress.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).location);
        resManagementFoodTypes.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).foodTypes.toString());
    }

    @FXML
    TextField editedFoodId;
    @FXML
    TextField editedDiscount;
    public void editFoodDiscount(ActionEvent e) throws IOException{
        for (int i = 0; i < admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.size() ; i++) {
            if(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(i).id==Integer.parseInt(editedFoodId.getText())) thisFood=i;
        }
        admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(thisFood).discountPercent = Double.parseDouble(editedDiscount.getText());
        whatToLoad = new String("Menu_admin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField editedPrice;
    public void editFoodPrice(ActionEvent e) throws IOException{
        for (int i = 0; i < admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.size() ; i++) {
            if(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(i).id==Integer.parseInt(editedFoodId.getText())) thisFood=i;
        }
        admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(thisFood).price = Double.parseDouble(editedPrice.getText());
        whatToLoad = new String("Menu_admin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    TextField editedExpirationDate;
    public void editFoodDiscountExpirationDate(ActionEvent e) throws IOException{
        for (int i = 0; i < admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.size() ; i++) {
            if(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(i).id==Integer.parseInt(editedFoodId.getText())) thisFood=i;
        }
        LocalDate today = LocalDate.now();
        admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(thisFood).discountExpirationDate = today.plusDays(Integer.parseInt(editedExpirationDate.getText()));
        whatToLoad = new String("Menu_admin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void editFoodAvailable(ActionEvent e) throws IOException{
        for (int i = 0; i < admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.size() ; i++) {
            if(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(i).id==Integer.parseInt(editedFoodId.getText())) thisFood=i;
        }
        admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(thisFood).isActive=true;
        whatToLoad = new String("Menu_admin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void editFoodUnavailable(ActionEvent e) throws IOException{
        for (int i = 0; i < admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.size() ; i++) {
            if(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(i).id==Integer.parseInt(editedFoodId.getText())) thisFood=i;
        }
        admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(thisFood).isActive=false;
        whatToLoad = new String("Menu_admin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void finishEdition(ActionEvent e) throws IOException{
        whatToLoad = new String("Restaurant_admin.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void resManagementComments(ActionEvent e) throws IOException{
        whatToLoad = new String("Comments.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////user panel functions
    @FXML
    TextField userSearchRes;
    @FXML
    Label shownRestaurants;
    public void userSearchedFirst(ActionEvent e) throws IOException{
        whatToLoad = new String("second_customer_panel.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void userSearchedSecond(ActionEvent e) throws IOException{
        foundRestaurants.clear();
        search = userSearchRes.getText();
        for (int i = 0; i < admins.size(); i++) {
            for (int j = 0; j < admins.get(i).restaurants.size(); j++) {
                if(search.equals(admins.get(i).restaurants.get(j).name)) foundRestaurants.add(admins.get(i).restaurants.get(j));
            }
        }

        String s= new String("");
        for (int i = 0; i < foundRestaurants.size(); i++) {
            s+=Integer.toString(foundRestaurants.get(i).id);
            s+=". ";
            s+=("Name: " + foundRestaurants.get(i).name);
            s+="   ";
            s+=("Address: " + foundRestaurants.get(i).location);
            s+="   ";
            s+=("foodTypes: " + foundRestaurants.get(i).foodTypes);
            s+="\n";
        }
        shownRestaurants.setText(s);
    }
    public void Home(ActionEvent e) throws IOException{
        m=0;
        n=0;
        whatToLoad = new String("First_login_register.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void userOrder(ActionEvent e) throws IOException{
        whatToLoad = new String("user_order.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void userOrderReturn(ActionEvent e) throws IOException{
        whatToLoad = new String("first_custmor_panel.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void userOrderEdit(ActionEvent e) throws IOException{
        whatToLoad = new String("Menu_client.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Label userOrderError;
    public void userOrderSubmit(ActionEvent e) throws IOException{
        if(users.get(thisUser).orderPrice() <= users.get(thisUser).charge) {
            Order newOrder = new Order(users.get(thisUser).activeOrder);
            users.get(thisUser).orderHistory.add(newOrder);
            users.get(thisUser).charge -= users.get(thisUser).orderPrice();
            users.get(thisUser).clear();
        }
        else{
            userOrderError.setText("your charge is not enough, please charge your account");
        }
    }

    @FXML
    Label userOrderDetails;
    public void userOrderSeeDetails(ActionEvent e) throws IOException{
        String s= new String("");
        s+=("server restaurant: " + admins.get(thisAdmin).restaurants.get(thisRestaurant).name + "\n");
        s+=("order price: " + users.get(thisUser).orderPrice() + "\n");
        for (int i = 0; i < users.get(thisUser).activeOrder.items.size(); i++) {
            s+=Integer.toString((i+1));
            s+=". ";
            s+=("Food: " + users.get(thisUser).activeOrder.items.get(i).name);
            s+="    ";
            s+=("food type: " + users.get(thisUser).activeOrder.items.get(i).type);
            s+="    ";
            s+=("price: " + users.get(thisUser).activeOrder.items.get(i).price);
            s+="    ";
            s+=("discount: " + users.get(thisUser).activeOrder.items.get(i).discountPercent + "%");
            s+="\n";
        }
        userOrderDetails.setText(s);
    }

    public void userAccount(ActionEvent e) throws IOException{
        whatToLoad = new String("user_account.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Label userAccountCharge;
    public void userAccountSeeCharge(ActionEvent e) throws IOException{
        userAccountCharge.setText(String.valueOf(users.get(thisUser).charge));
    }

    @FXML
    TextField userAccountCharged;
    public void userAccountCharge(ActionEvent e) throws IOException{
        users.get(thisUser).charge+=Double.parseDouble(userAccountCharged.getText());
        userAccountCharged.setText("");
    }

    public void userAccountReturn(ActionEvent e) throws IOException{
        whatToLoad = new String("first_custmor_panel.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Label userAccountOrderHistory;
    public void userAccountOrderHistory(ActionEvent e) throws IOException{
        String s= new String("");
        for (int i = 0; i < users.get(thisUser).orderHistory.size(); i++) {
            s+=Integer.toString(i+1);
            s+=". ";
            s+=("Items: " + users.get(thisUser).orderHistory.get(i).string());
            s+="   ";
            s+=("Order price: " + users.get(thisUser).orderHistory.get(i).Price());
            s+="   ";
            s+=("Server restaurant: " + users.get(thisUser).orderHistory.get(i).serverRestaurant);
            s+="\n";
        }
        userAccountOrderHistory.setText(s);
    }
    @FXML
    TextField userSelectedResId;
    public void userVisit(ActionEvent e) throws IOException{
        users.get(thisUser).activeOrder.items.clear();
        users.get(thisUser).activeOrder.serverRestaurant="".intern();

        for (int i = 0; i < admins.size(); i++) {
            for (int j = 0; j < admins.get(i).restaurants.size(); j++) {
                if(admins.get(i).restaurants.get(j).id == Integer.parseInt(userSelectedResId.getText())){
                    thisAdmin=i; thisRestaurant=j;
                }
            }
        }
        whatToLoad = new String("Restaurant_client.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Label userResName;
    @FXML
    Label userResAddress;
    @FXML
    Label userResFoodTypes;
    public void userResDetails(ActionEvent e)throws IOException{
        userResName.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).name);
        userResAddress.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).location);
        userResFoodTypes.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).foodTypes.toString());
    }

    public void userMenu(ActionEvent e) throws IOException{
        whatToLoad = new String("Menu_client.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    Label userMenuFoodName;
    @FXML
    Label userMenuFoodPrice;
    @FXML
    Label userMenuFoodDiscount;
    @FXML
    Label userMenuFoodType;
    @FXML
    Label userMenuGarb;
    public void userMenuNextFood(ActionEvent e) throws IOException{
        if(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.size() > m){
            m++;
            userMenuFoodName.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).name);
            userMenuFoodPrice.setText(Double.toString(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).price));
            userMenuFoodType.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).type);
            userMenuFoodDiscount.setText(Double.toString(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).discountPercent));
        }
        else{}
    }
    public void userMenuPreviousFood(ActionEvent e) throws IOException{
        if(0 < m){
            m--;
            userMenuFoodName.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).name);
            userMenuFoodPrice.setText(Double.toString(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).price));
            userMenuFoodType.setText(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).type);
            userMenuFoodDiscount.setText(Double.toString(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1).discountPercent));
        }
        else{}
    }

    public void userMenuAddToCourt(ActionEvent e) throws IOException{
        users.get(thisUser).activeOrder.serverRestaurant=admins.get(thisAdmin).restaurants.get(thisRestaurant).name.intern();
        users.get(thisUser).activeOrder.items.add(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1));
    }

    public void userMenuRemoveFromCourt(ActionEvent e) throws IOException{
        users.get(thisUser).activeOrder.items.remove(admins.get(thisAdmin).restaurants.get(thisRestaurant).menu.get(m-1));
    }
    public void userMenuReturn(ActionEvent e) throws IOException{
        m=0;
        whatToLoad = new String("first_custmor_panel.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    TextField resManagementFoodName;
    @FXML
    TextField resManagementFoodPrice;
    @FXML
    TextField resManagementFoodType;
    @FXML
    Label resAdminFoodId;
    public void resManagementAddFood(ActionEvent e) throws IOException{
        Food food = new Food(foodId , "" , 0);
        food.name=resManagementFoodName.getText();
        food.price=Double.parseDouble(resManagementFoodPrice.getText());
        food.type=resManagementFoodType.getText();
        food.discountPercent=0;
        LocalDate today = LocalDate.now();
        food.discountExpirationDate = today.plusDays(0);
        food.isActive=false;
        food.id=foodId;

        admins.get(admins.size()-1).restaurants.get(thisRestaurant).menu.add(food);
        admins.get(admins.size()-1).restaurants.get(thisRestaurant).foodTypes.add(resManagementFoodType.getText());

        resAdminFoodId.setText("food id for this added food is: " +foodId);

        foodId++;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    public void showComments(ActionEvent e)throws IOException{
        whatToLoad = new String("Comments.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    Label comments;
    public void displayComments(ActionEvent e)throws IOException{
        String w = new String("");

        for (int i = 0; i < admins.get(thisAdmin).restaurants.get(thisRestaurant).comments.size() ; i++) {
            w+=( (i+1) + ". ");
            w+= admins.get(thisAdmin).restaurants.get(thisRestaurant).comments.get(i);
            w+= "\n";
        }

        comments.setText(w);
    }

    @FXML
    TextField addedComment;
    public void addComment(ActionEvent e)throws IOException{
            admins.get(thisAdmin).restaurants.get(thisRestaurant).comments.add(addedComment.getText());
    }


    @FXML
    TextField forgotPassName;
    @FXML
    Label forgotPassLabel;
    public void forgotPass(ActionEvent e) throws IOException{
        name = forgotPassName.getText();
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).name.equals(name)) {forgotPassLabel.setText(admins.get(i).password); break;}
            else {forgotPassLabel.setText("username wrong");}
        }
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).name.equals(name)) {forgotPassLabel.setText(users.get(i).password); break;}
            else {forgotPassLabel.setText("username wrong");}
        }
        if(admins.size()==0 && users.size()==0) forgotPassLabel.setText("username wrong");

        /*whatToLoad = new String("forgot_password.fxml");
        root = FXMLLoader.load(getClass().getResource(whatToLoad));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {
        launch();

    }
}