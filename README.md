# TPA-Web
TPA-Web

- CrearArticuloJAXRSClient: http://localhost:8080/TPA-Web-0.0.1-SNAPSHOT/rest/service/articulo?articulo=
- SolicitudCompraFabricaClient: http://localhost:8080/TPA-Web-0.0.1-SNAPSHOT/rest/service/solicitudCompra?solicitud=

Colas

- java:/jms/queue/recepcionCompra
- java:/jms/queue/solicitudArticulo
- java:/jms/queue/testQueue

#JSON: Articulo

Tipos de articulos: 
Electro, Moda, Infantil, Mueble

Tipo de Datos:
idDeposito: String
codArticulo: Int
nombre: String
descripcion: String
marca: String
precio: Float
foto: String
origen: String
tipo: String
datosExtra: Map<String,String>

