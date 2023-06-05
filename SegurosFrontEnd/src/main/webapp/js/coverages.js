class Coverages {
    dom;
    
    categoryModal;
    coverageModal;
    
    state;
    
    constructor() {
        this.state = { 'entities': new Array(), 'entity': this.emptyEntity(), 'mode': 'A' };
        this.dom = this.render();
        this.categoryModal = new bootstrap.Modal(this.dom.querySelector("#createCategoryModal"));
        this.coverageModal = new bootstrap.Modal(this.dom.querySelector("#createCoverageModal"));
        
        this.dom.querySelector("#createCategory").addEventListener('click', e=>this.categoryModal.show());
        this.dom.querySelector("#createCoverage").addEventListener('click', e=>this.coverageModal.show());
        this.getCategories();
        
        this.dom.querySelector('#registerCategoryButton').addEventListener('click',e=>this.addCategory());
        this.dom.querySelector('#registerCoverageButton').addEventListener('click',e=>this.addCoverage());
    }
    
    render=()=> {
        const html = `
          ${this.renderList()}
          ${this.renderModal()}
        `;
        var rootContent = document.createElement('div');
        rootContent.id = 'coverages';
        rootContent.innerHTML = html;
        return rootContent;
    }
    
    renderList=()=> {
      return `
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">Coberturas por categoría</h1>
                    <div class="d-flex justify-content-end mb-3">
                        <button class="btn btn-primary" id="createCategory" data-bs-toggle="modal" data-bs-target="#createCategoryModal" style="margin-right: 5px">
                            <i class="fas fa-plus"></i> Crear categoría
                        </button>
                        <button class="btn btn-primary" id="createCoverage" data-bs-toggle="modal" data-bs-target="#createCoverageModal" style="margin-left: 5px">
                            <i class="fas fa-plus"></i> Crear cobertura
                        </button>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <div id="categories" class="table-responsive">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      `;
    };
    
    getCategories=async()=>{
        const categoriesRequest = new Request(`${backend}/categories`, {
            method:'GET',
            headers: {'Content-Type': 'application/json'}
        });
        
        try{
            const response = await fetch(categoriesRequest);
            
            if (!response.ok) {
               const error = await response.text();
               console.log("GET CATEGORIES ERROR - " + error);
               return;
            }
            
            console.log("CATEGORIES AQUIRED");
            const categoriesList = await response.json();
            this.state.categoriesList = categoriesList;
            this.renderCategories();
        }catch(err){
            console.error(err);
        }
    }
    
    renderCategories=()=>{
        const tableBody = this.dom.querySelector("#categories");
        tableBody.innerHTML = '';
        
        this.state.categoriesList.forEach((category)=> {
            const row = document.createElement('div');
            row.id = `category${category.id}`;
            row.innerHTML = `
                <div id="categoryIdNameDescription">
                    <h5>${category.id} - ${category.name}</h5>
                    <p>Descripción: ${category.description}</p>
                </div>
                <div id="categoryCoverages">
                    <table class="table table-striped" id="categoryCoveragesTable">
                        <thead>
                            <tr>
                                <th>Cobertura ID</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Costo mínimo</th>
                                <th>Porcentaje</th>
                            </tr>
                        </thead>
                        <tbody id="categoryCoveragesTableBody${category.id}">
                        </tbody>
                    </table>
                </div>
            `;
            tableBody.appendChild(row);
            this.renderCoverages(category);
        });
        
    }
    
    renderCoverages=(category)=>{
        try{
            const tableBody = this.dom.querySelector(`#categoryCoveragesTableBody${category.id}`);
            tableBody.innerHTML = '';
        
            category.coverages.forEach((coverage) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${coverage.id}</td>
                    <td>${coverage.name}</td>
                    <td>${coverage.description}</td>
                    <td>${coverage.minimumCost}</td>
                    <td>${coverage.percentage}</td>
                `;

                tableBody.appendChild(row);
            });
        }catch(err){
            console.error(err);
        }
    }
    
    renderModal=()=>{
        return `
            <div id="createCategoryModal" class="modal fade" tabindex="-1">
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
                                    <input type="text" class="form-control" id="name" name="name" required>
                                </div>
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Descripción</span>
                                    <input type="text" class="form-control" id="description" name="description" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="registerCategoryButton" type="button" class="btn btn-primary" >Aplicar</button>
                            </div>
                        </form>                 
                    </div>         
                </div>          
            </div>
        
            <div id="createCoverageModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" >
                            <h5 class="modal-title">Crear cobertura</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form id="coverageForm" >
                        <div class="modal-body">
                            <div class="input-group mb-3">
                                <span class="input-group-text">Nombre</span>
                                <input type="text" class="form-control" id="name" name="name" required>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Descripción</span>
                                <input type="text" class="form-control" id="description" name="description" required>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">ID categoría</span>
                                <input type="text" class="form-control" id="categoryId" name="categoryId" required>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Costo mínimo</span>
                                <input type="text" class="form-control" id="minimumCost" name="minimumCost" required>
                            </div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Porcentaje</span>
                                <input type="text" class="form-control" id="percentage" name="percentage" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="registerCoverageButton" type="button" class="btn btn-primary" >Aplicar</button>
                        </div>
                        </form>                 
                    </div>         
                </div>          
            </div>
        `;     
    }
  
    addCategory=async()=>{
        const candidate = Object.fromEntries( (new FormData(this.dom.querySelector("#categoryForm"))).entries());
        
        const registerCategoryRequest = new Request(`${backend}/categories`,{
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
  
    addCoverage=async()=>{
        const candidate = Object.fromEntries( (new FormData(this.dom.querySelector("#coverageForm"))).entries());
        
        const registerCategoryRequest = new Request(`${backend}/coverages`,{
            method:'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(candidate)
        });
        
        try{
            const response = await fetch(registerCategoryRequest);
            
            if(!response.ok){
                const error = await response.text();
                console.log("REGISTER COVERAGE ERROR - "+error);
                return;
            }
            
            console.log("REGISTER COVERAGE SUCCESSFUL");
            this.categoryModal.hide();
            this.getCategories();
        }catch(err){
            console.error(err);
        }
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
  
    makenew=()=>{
        this.reset();
        this.state.mode='A'; //adding
        this.showModal();
    }
    
    search=async()=>{
        
    }
}