class Administrator{
    dom;
    clientsModal;
    
    state;
    
    constructor(){
        this.state = {'entities': new Array(), 'entity': this.emptyEntity(), 'mode':'A'};
        this.dom = this.render();
        this.clientsModal = new bootstrap.Modal(this.dom.querySelector("#clientsModal"))
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
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>Nombre de Usuario</th>
                        </tr>
                      </thead>
                      <tbody id = "clientsTableBody">
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        `;
   }
   
   renderClientsList = (clients) => {
       const tableBody = this.dom.querySelector("#clientsTableBody");
       tableBody.innerHTML = '';
       
       clients.forEach((client) => {
           const row = document.createElement('tr');
           row.innerHTML = `
            <td>${client.name}</td>
            `;
            
            tableBody.appendChild(row);
       });
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
           this.renderClientsList(clientsList);
       } catch (err) {
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