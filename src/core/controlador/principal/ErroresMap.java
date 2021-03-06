/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.controlador.principal;
/**
 *
 * @author Tottus
 */
public class ErroresMap {

    
    
    public static String MessageError(int errorcode,String text){
        String errornombre="";
        switch(errorcode){
            case 1011 :    
               return errornombre="Ha ocurrido un error intentando borrar";
            case 1012 :
                errornombre="No puedo leer el registro en la tabla del sistema";break;
            case 1020 :    
                errornombre="El registro ha cambiado desde la ultima lectura de la tabla";break;
            case 1022 :
                return errornombre="El registro '"+text+"' ya existe";
            case 1032 :    
                return errornombre="No se pudo encontrar el registro";
            case 1034 :
                errornombre="Clave de archivo erronea para la tabla";break;
            case 1035 :    
                errornombre="Clave de archivo antigua para la tabla; reparelo";break;
            case 1040 :
                return errornombre="Existen demasiadas conexiones";
            case 1042 :    
                errornombre="No puedo obtener el nombre de maquina de tu direccion";break;
            case 1043 :
                errornombre="Protocolo erroneo ";break;
            case 1044:
                return errornombre="Acceso denegado el Usuario no tiene acceso a la base de datos ";
            case 1045 :
                return errornombre="Acceso denegado Usuario o Contraseña incorrectos ";
            case 1046 :    
                return errornombre="Base de datos no seleccionada ";
            case 1047 :
                errornombre="Comando desconocido";break;
            case 1048 :    
                errornombre="La columna no puede ser nula";break;
            case 1049 :
                return errornombre="Base de datos desconocida";
            case 1050 :    
                errornombre="La tabla ya existe";  break;
            case 1051 :
                errornombre="Existe una tabla desconocida";break;
            case 1053 :    
                errornombre="Desconexion de servidor en proceso";break;
            case 1056 :
                errornombre="No puedo agrupar";break;
            case 1057 :    
                errornombre="El estamento tiene funciones de suma y columnas en el mismo estamento";break;
            case 1058 :
                errornombre="La columna con count no tiene valores para contar";break;
            case 1060 :    
                errornombre="Nombre de columna duplicado";break;
            case 1061 :
                errornombre="Nombre de clave duplicado";break;
            case 1062 :    
                return errornombre="El registro '"+text+"' ya existe";
            case 1063 :
                errornombre="Especificador de columna erroneo para la columna";break;
            case 1065 :    
                errornombre="La query estaba vacia";break;
            case 1068 :
                errornombre="Multiples claves primarias definidas";break;
            case 1069 :    
                errornombre="Demasiadas claves primarias declaradas.";break;
            case 1072 :
                errornombre="La columna clave no existe en la tabla ";break;
            case 1090 :    
                errornombre="No puede borrar todos los campos con ALTER TABLE. Usa DROP TABLE para hacerlo";break;
            case 1091 :
                return errornombre="No se pudo eliminar por favor compruebe que el registro exista";
            case 1104 :    
                errornombre="El SELECT puede examinar muchos registros y probablemente con mucho tiempo. Verifique tu WHERE y usa SET SQL_BIG_SELECTS=1 si el SELECT esta correcto";break;     
            case 1105 :
                return errornombre="Error desconocido por favor contacte al administrador";
            case 1106 :    
                errornombre="Procedimiento desconocido";break;
            case 1107 :
                errornombre="Equivocado parametro count para procedimiento";break;
            case 1108 :    
                errornombre="Equivocados parametros para procedimiento";break;
            case 1109 :
                errornombre="Tabla desconocida";break;
            case 1111 :    
                errornombre=" Invalido uso de función en grupo";break;
            case 1113 :
                errornombre="Una tabla debe tener al menos 1 columna";break;
            case 1114 :    
                errornombre="La tabla está llena ";break;
            case 1115 :
                errornombre="Juego de caracteres desconocido:";break;
            case 1116 :    
                errornombre="Muchas tablas. MySQL solamente puede usar tablas en un join";break;
            case 1117 :
                errornombre="Muchos campos";break;
            case 1118 :    
                errornombre="Tamaño de línea muy grande. Máximo tamaño de línea, no contando blob. Tu tienes que cambiar algunos campos para blob";break;
            case 1120 :
                errornombre="Dependencia cruzada encontrada en OUTER JOIN. Examine su condición ON";break;
            case 1122 :    
                errornombre=" No puedo cargar función";break;
            case 1123 :
                errornombre="No puedo inicializar función ";break;
            case 1124 :    
                errornombre="No pasos permitidos para librarias conjugadas";  break;
            case 1127 :
                errornombre="No puedo encontrar función";break;
            case 1128 :    
                errornombre="Función no está definida";break;
            case 1129 :
                errornombre="Servidor está bloqueado por muchos errores de conexión. Desbloquear con 'mysqladmin flush-hosts'";break;
            case 1131 :    
                errornombre="Tu estás usando MySQL como un usuario anonimo y usuarios anonimos no tienen permiso para cambiar las claves";break;
            case 1132 :
                errornombre="Tu debes de tener permiso para actualizar tablas en la base de datos mysql para cambiar las claves para otros";break;
            case 1133 :    
                errornombre="No puedo encontrar una línea correspondiente en la tabla user ";break;
            case 1135 :
                errornombre="No puedo crear un nuevo thread (errno). Si tu está con falta de memoria disponible, tu puedes consultar el Manual para posibles problemas con SO";break;
            case 1138 :    
                errornombre=" Invalido uso de valor NULL ";break;
            case 1140 :
                errornombre="Mezcla de columnas GROUP (MIN(),MAX(),COUNT()...) con no GROUP columnas es ilegal si no hay la clausula GROUP BY ";break;
            case 1141 :    
                errornombre="No existe permiso definido para usuario";break;
            case 1144 :
                errornombre="Ilegal comando GRANT/REVOKE. Por favor consulte el manual para cuales permisos pueden ser usados.";break;
            case 1145 :    
                errornombre="El argumento para servidor o usuario para GRANT es demasiado grande";break;
            case 1146 :
                errornombre="Tabla no existe";break;
            case 1148 :    
                errornombre="El comando usado no es permitido con esta versión de MySQL";break;
            case 1149 :
                errornombre=" Algo está equivocado en su sintax ";  break;
            case 1157 :    
                errornombre="No puedo descomprimir paquetes de comunicación";break;
            case 1158 :
                errornombre="Obtenido un error leyendo paquetes de comunicación";break;
            case 1159 :    
                errornombre="Obtenido timeout leyendo paquetes de comunicación ";break;
            case 1160 :
                errornombre="Obtenido un error de escribiendo paquetes de comunicación";break;
            case 1161 :    
                errornombre="Obtenido timeout escribiendo paquetes de comunicación";  break;
            case 1163 :
                errornombre="El tipo de tabla usada no permite soporte para columnas BLOB/TEXT ";break;
            case 1164 :    
                errornombre="El tipo de tabla usada no permite soporte para columnas AUTO_INCREMENT";break;
            case 1166 :
                errornombre="Incorrecto nombre de columna";break;
            case 1167 :    
                errornombre="El manipulador de tabla usado no puede indexar columna";break;
            case 1169 :
                errornombre="No puedo escribir, debido al único constraint, para tabla";break;
            case 1171 :    
                errornombre="Todas las partes de un PRIMARY KEY deben ser NOT NULL; Si necesitas NULL en una clave, use UNIQUE ";break;
            case 1172 :
                errornombre="Resultado compuesto de mas que una línea ";break;
            case 1173 :    
                errornombre="Este tipo de tabla necesita de una primary key";break;
            case 1175 :
                errornombre="Tu estás usando modo de actualización segura intentado actualizar una tabla sin un WHERE que usa una KEY columna";break;
            case 1177 :    
                errornombre="No puedo abrir tabla";break;
            case 1180 :
                errornombre="Obtenido error %d durante COMMIT";break;
            case 1181 :    
                errornombre="Obtenido error %d durante ROLLBACK";break;
            case 1182 :
                errornombre="Obtenido error %d durante FLUSH_LOGS";break;
            case 1183 :    
                errornombre="Obtenido error %d durante CHECKPOINT";break;
            case 1191 :
                errornombre="No puedo encontrar índice FULLTEXT correspondiendo a la lista de columnas";break;
            case 1204 :
                errornombre="Tu solo debes usar expresiones constantes con SET";break;
            case 1205 :    
                errornombre="Tiempo de bloqueo de espera excedido";break;
            case 1206 :
                errornombre="El número total de bloqueos excede el tamaño de bloqueo de la tabla ";break;
            case 1210 :    
                errornombre="Argumentos errados";break;
            case 1214 :
                errornombre="El tipo de tabla usada no soporta índices FULLTEXT";  break;
            case 1215 :    
                errornombre="No puede adicionar clave extranjera constraint";break;
            case 1222 :
                errornombre="El comando SELECT usado tiene diferente número de columnas";break;
            case 1223 :    
                errornombre="No puedo ejecutar el query porque usted tiene conflicto de traba de lectura";break;
            case 1232 :
                errornombre="Tipo de argumento equivocado para variable";break;
            case 1235 :    
                errornombre="Esta versión de MySQL no soporta todavia";  break;
            case 1240 :
                errornombre="Referencia de llave y referencia de tabla no coinciden";break;
            case 1242 :    
                errornombre="Subconsulta retorna mas que 1 línea";break;
            case 1245 :
                errornombre="Cíclica referencia en subconsultas";break;
            case 1248 :    
                errornombre="Cada tabla derivada debe tener su propio alias";break;
            case 1251 :
                errornombre="Cliente no soporta protocolo de autenticación solicitado por el servidor; considere actualizar el cliente MySQL";break;
            case 1317 :    
                errornombre="Consulta En Execucion Fue Interrumpida";break;
            case 1359 :
                errornombre="El Trigger Ya Existe";break;
            case 1360 :    
                errornombre="El Trigger No Existe";break;
            case 1406 :
                return errornombre="Existen datos demasiado largos, sobrepasan el número máximo de caracteres'";
            case 2000 :
                errornombre=" El error desconocido MySQL";break;
            case 2002 :    
                errornombre="No puede conectarse al servidor local MySQL a través de conector ";break;
            case 2003 :
                errornombre="No puede conectarse al servidor MySQL en ";break;
            case 2008 :    
                errornombre="El cliente MySQL se quedó sin memoria";break;
            case 2050 :
                errornombre=" La fila que la recuperación fue cancelada por la llamada _ cercana mysql stmt ()";break;
            case 2052 :    
                errornombre="La declaración preparada no contiene metadatos";break;
            default :
                    return errornombre="Error desconocido por favor contacte al administrador";
        }
        
        return "Error Desconocido por favor contacte al administrador - "+errorcode;
    }
    
}
