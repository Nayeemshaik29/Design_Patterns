package BackendAPIDesign;

class User {

    int id;
    String password;
    String email;

    User(int id,String password,String email){
        this.id=id;
        this.password=password;
        this.email=email;
    }
}

class UserDTO {

    int id;
    String email;

    UserDTO(User user){

        this.id=user.id;
        this.email=user.email;
    }
}

public class DTOContractDemo {

    public static void main(String[] args){

        User user=new User(1,"secret123","abc@gmail.com");

        UserDTO dto=new UserDTO(user);

        System.out.println(dto.id);
        System.out.println(dto.email);
    }
}