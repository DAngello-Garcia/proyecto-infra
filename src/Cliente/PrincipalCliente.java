package Cliente;

import java.io.IOException;
import javax.swing.JOptionPane;

public class PrincipalCliente {
    private EchoTCPClient cliente;
    
    public static void main(String args[]) throws Exception
    {
        PrincipalCliente pc = new PrincipalCliente();
    }
    
    public PrincipalCliente() throws Exception
    {
        cliente = new EchoTCPClient();
        cliente.init();
        
        menu();
    }
    
    public void menu() throws IOException
    {
        int opcion;
        int cerrar;
        String numeroC, numeroCD;
        String respuesta;
        String nombre, clave;
        boolean logueado=false;
        String dinero;
        do
        {
           opcion = Integer.parseInt(JOptionPane.showInputDialog("Universidad del chavo \n\n"
                        + "1. Ingresar a la universidad \n"
                        + "2. Registrar carrera \n"
                        + "3. Registrar asignaturas \n"
                        + "4. Registrar matricula \n"
                        + "5. Transferir efectivo \n"
                        + "6. Salir"));
           
           switch (opcion)
           {
               case 1: nombre = JOptionPane.showInputDialog("Ingrese su nombre: ");
                       clave = JOptionPane.showInputDialog("Ingrese su clave: ");

                       cliente.enviarMensaje(opcion+";"+nombre+";"+clave);//envia los datos al servidor

                       respuesta = cliente.leerMensaje();
                       if (respuesta.equals("ok"))
                       {
                           logueado=true;
                           JOptionPane.showMessageDialog(null, "Bienvenido " + nombre);
                       }
                       else
                       {
                           JOptionPane.showMessageDialog(null, "Login o clave incorrecta. Vuelva a intentarlo");
                       }
                       break;
                       
               case 2: if (!logueado)
                       {
                           JOptionPane.showMessageDialog(null, "Logueese primero por favor!!!");
                       } 
                       else
                       {
                           numeroC = JOptionPane.showInputDialog("Ingrese numero de cuenta: ");
                           cliente.enviarMensaje(opcion+";"+numeroC);
                           respuesta = cliente.leerMensaje();
                           if (respuesta!="")
                             JOptionPane.showMessageDialog(null,respuesta);
                           else
                             JOptionPane.showMessageDialog(null,"Cuenta no encontrada");
                       }
                       break;
                       
               case 3: if (!logueado)
                       {
                           JOptionPane.showMessageDialog(null, "Logueese primero por favor!!!");
                       } 
                       else
                       {
                           numeroC = JOptionPane.showInputDialog("Ingrese numero de cuenta: ");
                           dinero = JOptionPane.showInputDialog("Ingrese la cantidad a consignar: ");
                           cliente.enviarMensaje(opcion+";"+numeroC+";"+dinero);
                           respuesta = cliente.leerMensaje();
                           if (respuesta!="")
                             JOptionPane.showMessageDialog(null,"Nuevos datos: \n "+respuesta);
                           else
                             JOptionPane.showMessageDialog(null,"Cuenta no encontrada");
                       }
                       break;
                       
               case 4: if (!logueado)
                       {
                           JOptionPane.showMessageDialog(null, "Logueese primero por favor!!!");
                       } 
                       else
                       {
                           numeroC = JOptionPane.showInputDialog("Ingrese numero de cuenta: ");
                           dinero = JOptionPane.showInputDialog("Ingrese la cantidad a retirar: ");
                           cliente.enviarMensaje(opcion+";"+numeroC+";"+dinero);
                           respuesta = cliente.leerMensaje();
                           if (respuesta!="")
                             JOptionPane.showMessageDialog(null,"nuevos datos: \n"+ respuesta);
                           else
                             JOptionPane.showMessageDialog(null,"Cuenta no encontrada");
                       }
                       break;
                   
               case 5: if (!logueado)
                       {
                           JOptionPane.showMessageDialog(null, "Logueese primero por favor!!!");
                       } 
                       else
                       {
                           numeroC = JOptionPane.showInputDialog("Ingrese numero de cuenta origen: ");
                           numeroCD = JOptionPane.showInputDialog("Ingrese numero de cuenta destino: ");
                           dinero = JOptionPane.showInputDialog("Ingrese la cantidad a transferir: ");
                           cliente.enviarMensaje(opcion+";"+numeroC+";"+numeroCD+";"+dinero);
                           respuesta = cliente.leerMensaje();
                           if (respuesta!="")
                             JOptionPane.showMessageDialog(null,"nuevos datos: \n"+ respuesta);
                           else
                             JOptionPane.showMessageDialog(null,"Cuenta no origen o destino encontrada");
                       }
                       break;
                   
               case 6: cerrar = Integer.parseInt(JOptionPane.showInputDialog("Seguro desea cerrar la aplicación ? 1/2 "));
                       if (cerrar == 1)
                       {
                           cliente.getClientSideSocket().close();
                           JOptionPane.showMessageDialog(null, "Cerrando aplicación");
                       }
                       break;
                           
               default: JOptionPane.showMessageDialog(null, "Opcion Incorrecta");
                        break;
               
                       
           }
           
        }
        while(opcion!=6);
        
    }
    
}
