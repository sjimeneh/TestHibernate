package view;
import controllers.userController;
import models.user;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String args[]) {

        userController oUserController = new userController();
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do{
            System.out.println("***** Bienvenido a la Gestión de Usuarios *****\n\n" +
                    "1. Listar todos los usuarios\n" +
                    "2. Buscar Usuario por ID\n" +
                    "3. Crear Usuario\n" +
                    "4. Actualizar usuario\n" +
                    "5. Eliminar usuario\n" +
                    "0. Salir\n\n" +
                    "*************************\n");
            try{
                System.out.print("Ingrese un Valor: ");
                option = sc.nextInt();

                switch(option){
                    case 1 ->{
                        ConsultarUsuarios(oUserController);
                    }
                    case 2 ->{
                        System.out.print("Ingrese el ID del empleado = ");
                        Long id = sc.nextLong();

                        ConsultarUsuarioPorId(oUserController,id);
                    }

                    case 3 ->{
                        CrearUsuario(oUserController,sc);
                    }

                    case 4 ->{
                        ActualizarUsuario(oUserController,sc);
                    }

                    case 5 ->{
                        EliminarUsuario(oUserController,sc);
                    }
                    case 0 -> System.out.println(">>>> Hasta Pronto <<<<");

                    default -> {
                        System.out.println(">>>> Opcion no valida <<<<");
                    }
                }

            }catch(NumberFormatException ex){
                System.out.println("El Dato ingresado no es Númericos");
            }



        }while(option != 0);


    }

    public static void ActualizarUsuario(userController oUserController, Scanner sc){
        System.out.println("Por favor Ingrese los datos del usuario que desea actualizar");
        System.out.print("ID = ");
        Long id = sc.nextLong();
        System.out.print("Nombre = ");
        String name = sc.next();
        System.out.print("Apellido = ");
        String lastName = sc.next();
        System.out.print("Edad = ");
        int age = sc.nextInt();

        user oUser = oUserController.updateUser(new user(id,name,lastName,age));

        if(oUser != null){
            System.out.println("Usuario Actualizado exitosamente");
            System.out.println(oUser.toString());
        }else{
            System.out.println("Error en la actualización del usuario");
        }
    }
    public static void EliminarUsuario(userController oUserController, Scanner sc){
        System.out.println("Por faovr Ingrese el ID del usuario a Eliminar");
        System.out.println("ID = ");
        Long id = sc.nextLong();
        boolean result = oUserController.DeleteUser(id);
        if(result){
            System.out.println("Usuario Eliminado Exitosamente!");
        }else{
            System.out.println("La eliminación del usuario ha fallado");
        }
    }
    public static void CrearUsuario(userController oUserController, Scanner sc){
        System.out.println("Por favor Ingrese los datos del usuario");
        System.out.print("Nombre = ");
        String name = sc.next();
        System.out.print("Apellido = ");
        String lastName = sc.next();
        System.out.print("Edad = ");
        int age = sc.nextInt();

        boolean result = oUserController.CreateUser(new user(null,name,lastName,age));

        if(result){
            System.out.println("Usuario Creado Exitosamente!");
        }else{
            System.out.println("La creación del usuario ha fallado");
        }

    }

    public static void  ConsultarUsuarios(userController oUserController){
        List<user> listUsers = oUserController.getAllUser();
        if(listUsers.isEmpty()){
            System.out.println("No hay usuarios disponibles");
        }else{
            for(user oUser : listUsers){
                System.out.println(oUser.toString());
            }
        }
    }

    public static void ConsultarUsuarioPorId(userController oUserController, Long id){
        user oUser = oUserController.FindUser(id);
        if(oUser != null){
            System.out.println(oUser.toString());
        }else{
            System.out.println("El usuario con ID = "+id+" No existe");
        }
    }
}
