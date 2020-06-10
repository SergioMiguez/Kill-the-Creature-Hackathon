package com.example.hospitapp;

/**
 * Constant class used to store all the URLS used to call the server and to make requests.
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
     * Public constant (String) used to store the URL that will connect to the server to receive a list of the not-linked orders.
     */
    public static String display_orders_url = "http://35.246.98.126/matalbicho/display_pedidos.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to receive a list of the linked (with an accepted provider) orders of a provider.
     */
    public static String display_connected_orders_url = "http://35.246.98.126/matalbicho/display_pedidos_conectados_proveedores.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to request to the database all the information about the user.
     */
    public static String profile_url = "http://35.246.98.126/matalbicho/profile_proveedores.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to register a new user in the database.
     */
    public static String providers_registry_url = "http://35.246.98.126/matalbicho/register_proveedores.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to request a list of all the materials present in the database.
     */
    public static String show_materials_url = "http://35.246.98.126/matalbicho/show_materiales.php";

    /**
     * Public constant (String) used to store the URL that will connect to the server to update the information about the user.
     */
    public static String update_profile_url = "http://35.246.98.126/matalbicho/update_perfil_proveedores.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to request a list of all sent orders (only sent).
     */
    public static String only_sent_url = "http://35.246.98.126/matalbicho/display_pedidos_enviados.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to inform that the user has received the order.
     */
    public static String confirm_received_url = "http://35.246.98.126/matalbicho/confirmar_recepcion_pedido.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to display all the orders,
     */
    public static String display_all_orders_url = "http://35.246.98.126/matalbicho/display_pedidos_proveedores.php";
    /**
     * Public constant (String) used to store the URL that will connect to the server to log in,
     */
    public static String provider_login_url = "http://35.246.98.126/matalbicho/login_proveedores.php";
    /**
     * Public constant (String) used to store the URL that will connect to volunteer to do an order.
     */
    public static String provider_link_order_request_url = "http://35.246.98.126/matalbicho/solicitar_pedido_proveedor.php";
    /**
     * Public constant (String) used to store the URL that will connect to mark as completed an order.
     */
    public static String mark_completed_url = "http://35.246.98.126/matalbicho/completar_pedido_proveedor.php";
    /**
     * Public constant (String) used to store the URL that will connect to mark as sent an order.
     */
    public static String mark_sent_url = "http://35.246.98.126/matalbicho/confirmar_envio_pedido_proveedores.php";
}