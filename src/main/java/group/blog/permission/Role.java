package group.blog.permission;

public enum Role {
    CommonUser("common-user"), Administrator("administrator");

    private final String name;

    Role(String name){
        this.name = name;
    }

    public String value(){
        return name;
    }

    public static Role getRole(String name){
        for (Role r : values()) {
            if (r.value().equals(name)){
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}