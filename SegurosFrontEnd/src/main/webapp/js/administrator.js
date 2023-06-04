class Administrator{
    dom;
    clientsModal;
    
    state;
    
    constructor(){
        this.state = {'entities': new Array(), 'entity': this.emptyEntity(), 'mode':'A'};
        this.dom = this.render();
        this.clientsModal = new bootstrap.Modal(this.dom.querySelector("#clientsModal"));
//        this.dom.querySelector("#search").addEventListener('click',this.search);
//        this.dom.querySelector('#policies #modal #form #apply').addEventListener('click',this.add);
//        this.modal = new bootstrap.Modal(this.dom.querySelector('#modal'));
//        this.clients = new Clients();
//        this.dom.querySelector("#policies #create").addEventListener('click',this.makenew);        
    }
    
    render=()=>{
        const html=`
            ${this.renderList()}
            ${this.renderModal()}
        `;
        var rootContent= document.createElement('div');
        rootContent.id='administrator';       
        rootContent.innerHTML=html;
        return rootContent;
    }
    
    renderList=()=>{
     return `
        <div class="container mt-5">
          <div class="row">
            <div class="col-md-12">
              <h1 class="text-center">Pólizas por Usuarios</h1>
              <div class="card">
                <div class="card-body">
                  <div class="input-group mb-3">
                    <input type="text" class="form-control" id="searchInput" placeholder="Buscar por número de usuario">
                    <button class="btn btn-outline-secondary" type="button" id="searchButton">Buscar</button>
                  </div>
                  <div id="clients" class="table-responsive">
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        `;
   }
   
    renderClients = async () => {
       const clientsRequest = new Request(`${backend}/clients`, {
          method:'GET',
          headers: {'Content-Type': 'application/json'}
       });
       
       try {
           const response = await fetch(clientsRequest);
           
           if (!response.ok) {
               const error = await response.text();
               console.log("GET CLIENTS ERROR - " + error);
               return;
           }
           
           console.log("CLIENTS AQUIRED");
           const clientsList = await response.json();
           this.state.clientsList = clientsList;
           this.renderClientsList();
       } catch (err) {
           console.error(err);
       }
    }
   
    renderClientsList = async () => {
        const tableBody = this.dom.querySelector("#clients");
        tableBody.innerHTML = '';
       
        this.state.clientsList.forEach((client) => {
            const row = document.createElement('div');
            row.innerHTML = `
                <h5>${client.id} - ${client.name}</h5>
                <div id="clientPolices" class="table-responsive">
                    <table class="table table-striped" id="clientPolicesTable">
                        <thead>
                            <tr>
                                <th>Número de Póliza</th>
                                <th>Número de Placa</th>
                                <th>Fecha</th>
                                <th>Automóvil</th>
                                <th>Valor</th>
                                <th></th>
                                <th>Plaza</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody id="policiesTableBody${client.id}">
                        </tbody>
                    </table>
                </div>
            `;
            tableBody.appendChild(row);
            this.renderClientPolicies(client);
        });
    }   
   
    renderClientPolicies = async (client) => {
       const clientPoliciesRequest = new Request (`${backend}/policies?id=${client.id}`, {
          method:'GET',
          headers: {'Content-Type': 'application/json'}
       });
       
        try {
           const response = await fetch(clientPoliciesRequest);
           if (!response.ok) {
               const error = await response.text();
               console.log("GET CLIENT POLICIES ERROR - " + error);
               return;
           }
           console.log("CLIENT POLICIES AQUIRED");
           const clientPoliciesList = await response.json();
           
           const tableBody = this.dom.querySelector(`#policiesTableBody${client.id}`);
           tableBody.innerHTML = '';
        
           clientPoliciesList.forEach((policy) => {
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
                    <td><button id="${buttonId}" class="btn btn-sm data-id="${policy.id}"><i class="fas fa-search"></i></button></td>
                `;

                //this.dom.querySelect(`#${buttonId}`)?.addEventListener('click', e=>this.showPolicyDetails());
                const button = row.querySelector('button');
                button.addEventListener('click', e=>this.showPolicyDetails());

                tableBody.appendChild(row);
            });
        }catch (err) {
           console.error(err);
        }
    }
   
   renderModal=()=>{
     return `
        <div id="clientsModal" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header" >
                        <img class="img-circle" id="img_logo" src="images/Logotipo.png" style="max-width: 50px; max-height: 50px" alt="logo">
                        <span style='margin-left:4em;font-weight: bold;'>Country</span> 
                       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form id="form" >
                    <div class="modal-body">
                        <h1>To do</h1>       
                    </div>
                    <div class="modal-footer">
                        <button id="apply" type="button" class="btn btn-primary" >Aplicar</button>
                    </div>
                    </form>                 
                </div>         
            </div>          
        </div>      
        `;     
    }

    showModal= async ()=>{
        // Load entity data into modal form
        this.modal.show();
  }
  
    load=()=>{
        // Save modal form data into entity        
    }
    
    reset=()=>{
        this.state.entity=this.emptyEntity();
    }
    
    emptyEntity=()=>{
        // return an empty entity
    }


  add=()=>{
      // Validate data, load into entity, invoque backend for adding
    this.list();
    this.reset();
    this.modal.hide();
  } 
  
  update=()=>{
    // Validate data, load into entity, invoque backend for updating    
    this.list();
    this.reset();
    this.modal.hide();
  }
  
   validate=()=>{
       // validate data
  }

  list=()=>{
    const request = new Request(`${backend}/policies`, {method: 'GET', headers: { }});
    (async ()=>{
        const response = await fetch(request);
        if (!response.ok) {errorMessage(response.status);return;}
        var policies = await response.json();
        this.state.entities = policies;
        var listing=this.dom.querySelector("#policies #list #listbody");
        listing.innerHTML="";
        this.state.entities.forEach( e=>this.row(listing,e));         
    })();       
  }  

  row=(list,c)=>{
	var tr =document.createElement("tr");
	tr.innerHTML=`
                <td>${c.name}</td>
                <td><img class="flag" src="${c.flag}"></td>`;              
	list.append(tr);           
  }
  
  makenew=()=>{
      this.reset();
      this.state.mode='A'; //adding
      this.showModal();
  }
    
  search=()=>{
      // To do
  }
}