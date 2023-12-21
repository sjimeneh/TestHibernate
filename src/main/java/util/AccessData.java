package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AccessData {

    private static final SessionFactory oSessionFactory;

    static {
        try {
            // Cargar configuración desde hibernate.cfg.xml
            Configuration oConfiguration = new Configuration().configure("hibernate.cfg.xml");

            // Construir la sesión de fábrica
            oSessionFactory = oConfiguration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al inicializar la SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Obtener una nueva sesión
    public static Session getSession() {
        return oSessionFactory.openSession();
    }

    // Cerrar la sesión de manera segura
    public static void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    // Cerrar la SessionFactory
    public static void shutdown() {
        if (oSessionFactory != null) {
            oSessionFactory.close();
        }
    }
}
