class Vehicles {
    dom;
    state;
    addVehicleModal;
    
    constructor() {
        this.state = { 'entities': new Array(), 'entity': this.emptyEntity(), 'mode': 'A' };
        this.dom = this.render();
        this.renderVehicles();
        this.addVehicleModal = new bootstrap.Modal(this.dom.querySelector("#addVehicle"));
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
                    <button class="btn btn-primary" id="addVehicle" data-bs-toggle="modal" data-bs-target="#addVehicleModal" style="margin-right: 5px">Añadir Vehículo</button>
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
}