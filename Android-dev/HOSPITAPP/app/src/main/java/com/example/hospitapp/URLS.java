package com.example.hospitapp;

/**
 * Public constant class that is used to store all the URLS used in the application.
 */
public class URLS {
    /**
     * Private constructor to avoid instantiation.
     */
    private URLS() {}

    /**
     * Public constant (String) used to store the URL that will connect to the server to add a new material to the database.
     */
    public static String add_material_url = "http://35.246.98.126/matalbicho/anadir_material.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to receive a list of the not-linked orders done by a user.
     */
    public static String display_orders_url = "http://35.246.98.126/matalbicho/display_pedidos.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to receive a list of the linked (with an accepted provider) orders done by a user.
     */
    public static String display_connected_orders_url = "http://35.246.98.126/matalbicho/display_pedidos_conectados.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to allow the log in of a user in the app.
     */
    public static String login_url = "http://35.246.98.126/matalbicho/login.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to create a new order and add it to the database, maintaining the address.
     */
    public static String new_order_with_same_address_url = "http://35.246.98.126/matalbicho/nuevo_pedido_con_direccion.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to create a new order and add it to the database, providing a new address.
     */
    public static String new_order_with_new_address_url = "http://35.246.98.126/matalbicho/nuevo_pedido_nueva_direccion.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to request to the database all the information about the user.
     */
    public static String profile_url = "http://35.246.98.126/matalbicho/profile.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to register a new user in the database.
     */
    public static String hospital_registry_url = "http://35.246.98.126/matalbicho/registro_hospitales.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to request a list of all the materials present in the database.
     */
    public static String show_materials_url = "http://35.246.98.126/matalbicho/show_materiales.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to request a list of all the requests of different providers to make an order.
     */
    public static String show_potential_providers_url = "http://35.246.98.126/matalbicho/solicitudes_pedidos_con_proveedor.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to link an order with one of the potential providers.
     */
    public static String connect_orders_url = "http://35.246.98.126/matalbicho/transition_home_complete.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to update the information about the user.
     */
    public static String update_profile_url = "http://35.246.98.126/matalbicho/update_perfil.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to request a list of all sent orders (only sent).
     */
    public static String only_sent_url = "http://35.246.98.126/matalbicho/display_pedidos_enviados.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to inform that the user has received the order.
     */
    public static String confirm_received_url = "http://35.246.98.126/matalbicho/confirmar_recepcion_pedido.php";
}