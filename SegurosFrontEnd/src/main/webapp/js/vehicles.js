class Vehicles {
    dom;
    state;
    
    constructor() {
        this.state = { 'entities': new Array(), 'entity': this.emptyEntity(), 'mode': 'A' };
        this.dom = this.render();
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
               console.log("GET CLIENTS ERROR - " + error);
               return;
           }
           
           console.log("CLIENTS AQUIRED");
           const vehiclesList = await response.json();
           this.state.clientsList = vehiclesList;
           this.renderVehiclesList();
       } catch (err) {
           console.error(err);
       }
    }
    
    renderVehiclesList = async () => {
        const tableBody = this.dom.querySelector("#vehicles");
        tableBody.innerHTML = '';
       
        this.state.vehiclesList.forEach((vehicle) => {
            const row = document.createElement('div');
            const buttonId = `details-${vehicle.brand}`;
            row.innerHTML = `
                <h5>${vehicle.id} - ${vehicle.brand}</h5>
                <div id="vehiclesAdmin" class="table-responsive">
                    <table class="table table-striped" id="vehiclesTable">
                        <thead>
                            <tr>
                                <th>Modelo</th>
                                <th>Año</th>
                                <th>Imagen</th>
                            </tr>
                        </thead>
                        <tbody id="vehiclesTableBody${vehicle.id}">
                        </tbody>
                    </table>
                </div>
            `;
            //const button = row.querySelector('button');
            //button.addEventListener('click', e=>this.showPolicyDetails(vehicle));
            tableBody.appendChild(row);
            this.renderVehicles(vehicle);
        });
    }   
    
    renderVehicles = async (vehicle) => {
       const vehiclesRequest = new Request (`${backend}/vehicles?id=${vehicle.id}`, {
          method:'GET',
          headers: {'Content-Type': 'application/json'}
       });
       
        try {
           const response = await fetch(vehiclesRequest);
           if (!response.ok) {
               const error = await response.text();
               console.log("GET CLIENT POLICIES ERROR - " + error);
               return;
           }
           console.log("CLIENT POLICIES AQUIRED");
           const vehiclesList = await response.json();
            
           const tableBody = this.dom.querySelector(`#policiesTableBody${vehicle.id}`);
           tableBody.innerHTML = '';
        
           vehiclesList.forEach((policy) => {
                const row = document.createElement('tr');
                const buttonId = `details-${policy.id}`;
                row.innerHTML = `
                    <td>${policy.id}</td>
                    <td>${policy.license}</td>
                    <td>${policy.initialDate}</td>
                    <td>${policy.vehicle.brand} - ${policy.vehicle.model}</td>
                    <td><img src="${backend}/vehicles/${policy.vehicle.id}/image" style="display: block; margin: 0 auto; max-width: 200px; max-height: 200px;"></td>
                    <td>${policy.insuredValue}</td>
                    <td>${policy.term}</td>
                `;
                tableBody.appendChild(row);
            });
            
        }catch (err) {
           console.error(err);
        }
    }
}