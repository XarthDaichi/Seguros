class Vehicles {
    dom;
    modal;
    state;
    
    constructor() {
        this.state = { 'entities': new Array(), 'entity': this.emptyEntity(), 'mode': 'A' };
        this.dom = this.render();
        this.modal = new bootstrap.Modal(this.dom.querySelector("#createVehicle"));
        this.renderVehicles();
        
        this.dom.querySelector('#registerVehicle').addEventListener('click',e=>this.addVehicle());
    }
    
    render=()=> {
        const html = `
          ${this.renderList()}
        `;
        var rootContent = document.createElement('div');
        rootContent.id = 'vehicles';
        rootContent.innerHTML = html;
        return rootContent;
    }
    
    renderList=()=> {
      return `
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">Modelos por marca</h1>
                    <div class="card">
                        <div class="card-body">
                            <div id="vehicles" class="table-responsive">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      `;
    };
    
    emptyEntity =()=> {
    }
    
    renderVehicles = async () => {
       const vehiclesRequest = new Request(`${backend}/vehicles`, {
          method:'GET',
          headers: {'Content-Type': 'application/json'}
       });
       
       try {
           const response = await fetch(vehiclesRequest);
           
           if (!response.ok) {
               const error = await response.text();
               console.log("GET VEHICLES ERROR - " + error);
               return;
           }
           
           console.log("VEHICLE AQUIRED");
           const vehiclesList = await response.json();
           this.state.vehiclesList = vehiclesList;
           this.renderVehiclesList();
       } catch (err) {
           console.error(err);
       }
    }
    
    renderVehiclesList = async () => {
        const tableBody = this.dom.querySelector("#vehicles");
        tableBody.innerHTML = '';
        this.state.vehiclesList.forEach((brand) => {
            const row = document.createElement('div');
            row.innerHTML = `
                <h5>${brand[0].id} - ${brand[0].brand}</h5>
                <div id="vehiclesAdmin" class="table-responsive">
                    <table class="table table-striped" id="vehiclesTable">
                        <thead>
                            <tr>
                                <th>Modelo</th>
                                <th>Año</th>
                                <th>Imagen</th>
                            </tr>
                        </thead>
                        <tbody id="vehiclesTableBody${brand[0].id}">
                        </tbody>
                    </table>
                </div>
            `;
            //const button = row.querySelector('button');
            //button.addEventListener('click', e=>this.showPolicyDetails(vehicle));
            tableBody.appendChild(row);
            this.renderModels(brand);
        });
    }   
    
    renderModels=(brand)=>{
        try{
            const tableBody = this.dom.querySelector(`#vehiclesTableBody${brand[0].id}`);
            tableBody.innerHTML = '';

            brand.forEach((model) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${model.model}</td>
                    <td>${model.year}</td>
                    <td><img src="${backend}/vehicles/${model.id}/image" style="display: block; margin: 0 auto; max-width: 200px; max-height: 200px;"></td>
                `;

                tableBody.appendChild(row);
            });
        }catch(err){
            console.error(err);
        }
    }
    
    renderModal=()=>{
        return `
            <div id="createVehicle" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" >
                            <h5 class="modal-title">Crear categoría de coberturas</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="categoryForm" >
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Nombre</span>
                                    <input type="text" class="form-control" id="brand" name="brand" required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Descripción</span>
                                    <input type="text" class="form-control" id="model" name="model" required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Descripción</span>
                                    <input type="text" class="form-control" id="year" name="year" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="registerVehicle" type="button" class="btn btn-primary" >Aplicar</button>
                            </div>
                        </form>                 
                    </div>         
                </div>          
            </div>
        `;
    }
    
    addVehicle=async()=>{
        const candidate = Object.fromEntries( (new FormData(this.dom.querySelector("#vehiclesForm"))).entries());
        candidate.id="";
        
        const registerCategoryRequest = new Request(`${backend}/vehicles`,{
            method:'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(candidate)
        });
        
        try{
            const response = await fetch(registerCategoryRequest);
            
            if(!response.ok){
                const error = await response.text();
                console.log("REGISTER CATEGORY ERROR - "+error);
                return;
            }
            
            console.log("REGISTER CATEGORY SUCCESSFUL");
            this.categoryModal.hide();
            this.getCategories();
        }catch(err){
            console.error(err);
        }
    }
}