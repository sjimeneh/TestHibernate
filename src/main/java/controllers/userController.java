package controllers;
import models.user;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import util.AccessData;


public class userController {




    public boolean CreateUser(user oUser){

        boolean aux = false;

        try (Session oSession = AccessData.getSession()){

            oSession.beginTransaction();
            oSession.save(oUser);
            oSession.getTransaction().commit();
            aux = true;

        }catch (Exception e){
            System.out.println("La creación del usuario ha fallado");
        }

        return aux;
    }

    public user updateUser(user oUser){
            user oUserAux = null;
        try (Session oSession = AccessData.getSession()){
            oSession.beginTransaction();
            oSession.update(oUser);
            oSession.getTransaction().commit();
            oUserAux = oUser;
        }catch (Exception e){
            System.out.println("Error al Actualizar el usuario");
        }
        return oUserAux;
    }

    public user FindUser(Long id){
        user oUserResult = null;

        try (Session oSession = AccessData.getSession()){
            oUserResult = oSession.get(user.class,id);

        }catch (Exception e){
            System.out.println("Error en la consulta del usuario");
        }

        return oUserResult;
    }

    public boolean DeleteUser(Long id){
        boolean aux = false;
        try (Session oSession = AccessData.getSession()){

            user oUser = FindUser(id);

            if(oUser != null){
                oSession.beginTransaction();
                oSession.delete(oUser);
                oSession.getTransaction().commit();
                aux = true;
            }else{
                System.out.println("El usuario No Existe");
            }

        }catch (Exception e){
            System.out.println("Error al Eliminar el usuario con ID = "+id);
        }
        return aux;
    }

    public List<user> getAllUser(){
        List<user> listaUsuarios = null;
        try (Session oSession = AccessData.getSession()){
            listaUsuarios = oSession.createQuery("FROM user",user.class).getResultList();

        }catch (Exception e){
            System.out.println("Error al consultar los usuarios\n"+e.getMessage());
        }
        return listaUsuarios;
    }
}
