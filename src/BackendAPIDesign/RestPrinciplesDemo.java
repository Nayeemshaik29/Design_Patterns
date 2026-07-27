package BackendAPIDesign;

class RestPrinciplesDemo {

    public void getUser() {
        System.out.println("GET /users/101");
    }

    public void createUser() {
        System.out.println("POST /users");
    }

    public void updateUser() {
        System.out.println("PUT /users/101");
    }

    public void partialUpdate() {
        System.out.println("PATCH /users/101");
    }

    public void deleteUser() {
        System.out.println("DELETE /users/101");
    }

    public static void main(String[] args) {

        RestPrinciplesDemo api = new RestPrinciplesDemo();

        api.getUser();
        api.createUser();
        api.updateUser();
        api.partialUpdate();
        api.deleteUser();
    }
}