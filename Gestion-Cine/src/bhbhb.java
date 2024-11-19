/*/// CARGAR CLIENTES DESDE EL ARCHIVO JSON -------------------------------------------------------------------------

private void cargarClientes(){
    Gson gson = new Gson();

    //Leo el lastID del archivo de usuarios.
    try (FileReader lastIdReader = new FileReader(LAST_ID_FILE_PATH)){
        JsonObject lastIdData = gson.fromJson(lastIdReader, JsonObject.class);
        if(lastIdData != null && lastIdData.has("lastId")){
            this.lastId = lastIdData.get("lastId").getAsInt();
        }
    }catch (IOException e){
        System.out.printf("No se pudo cargar el archivo que contiene el ultimo ID: " + e.getMessage());
    }

    //Leer el archivo de usuarios
    try (FileReader clientesReader = new FileReader(CLIENTES_FILE_PATH)){
        JsonObject data = gson.fromJson(clientesReader, JsonObject.class);

        if(data != null){
            if(data.has("clientes")){
                Type mapType = new TypeToken<HashMap<String,Cliente>>() {}.getType();
                Map<String,Cliente> clientesCargados = gson.fromJson(data.get("clientes"),mapType);
                if (clientesCargados != null){
                    this.clientes = clientesCargados;
                }
            }
        }
    }catch (IOException e){
        System.out.println("No se pudo cargar el archivo de clientes: " +e.getMessage());
    }
}

/// GUARDAR CLIENTES EN EL ARCHIVO JSON -------------------------------------------------------------------------

private void guardarClientes(){
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    try (FileWriter clientesWriter = new FileWriter(CLIENTES_FILE_PATH)){
        JsonObject data = new JsonObject();

        //Guardo los clientes
        data.add("clientes",gson.toJsonTree(this.clientes));

        //Escribo los clientes en el archivo
        gson.toJson(data,clientesWriter);
    }catch (IOException e){
        System.out.println("No se pudo guardar el archivo JSON: " +e.getMessage());
    }
}
*/
