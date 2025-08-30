
# MICROSERVICES DEMO

Aplicación de **microservicios** con **APIs REST** desarrollada en **Spring Boot**, que implementa todas las operaciones CRUD.  

Está desplegada en un plan gratuito, por lo que:  
- El servidor puede tardar varios minutos en iniciar tras un período de inactividad.  
- Todos los cambios realizados son **temporales** y se restablecen periódicamente.  

**Deploy del proyecto en Render utilizando Docker:**

- 🔗 [Eureka](https://microserv-v2.onrender.com)

- 🔗 [Gateway](https://microserv-v2-gateway.onrender.com/users/details)

- 🔗 [Product-Service](https://microserv-v2-product.onrender.com/product)

- 🔗 [Users-Service](https://microserv-v2-user.onrender.com/users)

También cuenta con una interfaz en **React** para probar los endpoints de forma más visual:  
🔗 [**Interfaz de Usuario**](https://microservices-demo.netlify.app/)
  
📂 [Repositorio UI](https://github.com/GustaAltF4/React-Practicas/tree/main/8-%20UI%20microserv/microserv-iu)


---

## API Reference 🛠
Ejemplos de algunos endpoints implementados:

- **Get data by id or name**

```http
  GET /users/id/{id}
  GET /users/name/{name}
  GET /product/id/{id}
  GET /product/name/{name}
```


- **Post**

```http
  POST /users/add
  POST /product/add
```

- **Update**

```http
  PUT /users/update/{id}
  PUT /product/update/{id}
```

- **Delete**

```http
  DELETE /users/del/id/{id}
  DELETE /product/del/id/{id}
```



