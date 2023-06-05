class Policies {
    dom;
    createPolicyModal;
    policyDetailsModal;

    state;

    constructor() {
        this.state = { 'entities': new Array(), 'entity': this.emptyEntity(), 'mode': 'A' };
        this.dom = this.render();
        this.createPolicyModal = new bootstrap.Modal(this.dom.querySelector("#createPolicyModal"));
        this.policyDetailsModal = new bootstrap.Modal(this.dom.querySelector("#policyModal"));
        this.dom.querySelector("#createPolicy").addEventListener('click', e=>this.showModal());
        this.dom.querySelector("#searchButton").addEventListener('click', e=>this.search());
        this.dom.querySelector('#siguienteBtn').addEventListener('click', e=>this.validateAndProceed);
    }

    render = () => {
        const html = `
          ${this.renderList()}
          ${this.renderModal()}
        `;
        var rootContent = document.createElement('div');
        rootContent.id = 'policies';
        rootContent.innerHTML = html;
        return rootContent;
    }
    
    renderList = () => {
      return `
        <div class="container mt-5">
          <div class="row">
            <div class="col-md-12">
              <h1 class="text-center">Pólizas de Seguro</h1>
              <div class="d-flex justify-content-end mb-3">
                <button class="btn btn-primary" id="createPolicy" data-bs-toggle="modal" data-bs-target="#crearPolizaModal">
                  <i class="fas fa-plus"></i> Crear Póliza
                </button>
              </div>
              <div class="card">
                <div class="card-body">
                  <div class="input-group mb-3">
                    <input type="text" class="form-control" id="searchInput" placeholder="Buscar por número de placa">
                    <button class="btn btn-outline-secondary" type="button" id="searchButton">Buscar</button>
                  </div>
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th scope="col">Número de Póliza</th>
                          <th scope="col">Número de Placa</th>
                          <th scope="col">Fecha</th>
                          <th scope="col">Automóvil</th>
                          <th scope="col">Valor</th>
                          <th scope="col"></th>
                          <th scope="col">Plaza</th>
                          <th scope="col">Acciones</th>
                        </tr>
                      </thead>
                      <tbody id = "policiesTableBody">
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      `;
    };
    
    //RenderPoliciesList: Pone en cada row, los datos de state.policies.
    renderPoliciesList = (policies) => {
        const tableBody = this.dom.querySelector('#policiesTableBody');
        tableBody.innerHTML = '';
        
        policies.forEach((policy) => {
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
            <td><button id="${buttonId}" class="btn btn-primary btn-sm" data-id="${policy.id}"><i class="fas fa-search"></i>Ver</button></td>
           `;
            
//            this.dom.querySelect(`#${buttonId}`)?.addEventListener('click', e=>this.showPolicyDetails());
            const button = row.querySelector('button');
            button.addEventListener('click', e=>this.showPolicyDetails(e));
            
            tableBody.appendChild(row);
        });
    };
    
    //RenderPolicies: Hace la petición de polizas al backend
    
    renderPolicies = async () => {
        const policiesRequest = new Request(`${backend}/policies?id=${globalstate.user.id}`,{
            method:'GET',
            headers: {'Content-Type': 'application/json'}
        });
        
        try{
            
            const response = await fetch(policiesRequest);
            
            if(!response.ok){
                const error = await response.text();
                console.log("GET POLICIES ERROR - "+error);
                return;
            }
            
            console.log("POLICIES AQUIRED");
            const policiesList = await response.json();
            this.state.policiesList = policiesList;
            this.renderPoliciesList(policiesList);
        }catch(err){
            console.error(err);
        }
    }

    

    renderModal = () => {
        return `
            <div id="createPolicyModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Crear Póliza de Seguro</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <ul class="nav nav-tabs" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="datosBasicosTab" data-bs-toggle="tab" data-bs-target="#datosBasicos" type="button" role="tab" aria-controls="datosBasicos" aria-selected="true">Datos Básicos</button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="coberturasTab" data-bs-toggle="tab" data-bs-target="#coberturas" type="button" role="tab" aria-controls="coberturas" aria-selected="false">Coberturas</button>
                                </li>
                            </ul>
                            <div class="tab-content mt-3">
                                <div class="tab-pane fade show active" id="datosBasicos" role="tabpanel" aria-labelledby="datosBasicosTab">
                                    <form id="addPolicyForm">
                                        <div class="mb-3">
                                            <label for="license" class="form-label">Número de Placa del Vehículo</label>
                                            <input type="text" class="form-control" id="license" placeholder="Ingrese el número de placa del vehículo" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="vehicle" class="form-label">Marca y Modelo del Vehículo</label>
                                            <select class="form-select" id="vehicle" required>
                                                <option value="">Seleccione la marca, modelo y año del vehículo...</option>
                                                <!-- Opciones de marca y modelo aquí con renderVehiculos-->
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="insuredValue" class="form-label">Valor Asegurado en Colones</label>
                                            <input type="text" class="form-control" id="insuredValue" placeholder="Ingrese el valor asegurado en colones" required>
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Plaza de la Póliza</label>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="termChosen" id="QUARTERLY" required>
                                                <label class="form-check-label" for="QUARTERLY">Trimestral</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="termChosen" id="BIANNUAL" required>
                                                <label class="form-check-label" for="BIANNUAL">Semestral</label>
                                            </div>
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="termChosen" id="ANNUAL" required>
                                                <label class="form-check-label" for="ANNUAL">Anual</label>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane fade" id="coberturas" role="tabpanel" aria-labelledby="coberturasTab">
                                    <div class="scrollable" id="listaCoberturas">
                                      <!-- Renderizar las categorías y sus coberturas en load -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                          <button id="siguienteBtn" type="button" class="btn btn-primary">Siguiente</button>
                        </div>
                    </div>
                </div>
            </div>
        
            <div id="policyModal" class="modal fade" tabindex="-1">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Detalles de Poliza</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div id="modal-details-content" class="modal-body">
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
        `;
    };
    
    emptyEntity = () => {

    }

    search = () => {
        const searchInput = this.dom.querySelector('#searchInput').value.toLowerCase();

        let filteredPolicies;
        if (searchInput === '') {
            filteredPolicies = this.state.policiesList;
        } else {
            filteredPolicies = this.state.policiesList.filter(policy => {
                const { license } = policy;
                return license.toLowerCase().includes(searchInput);
            });
        }

        this.renderPoliciesList(filteredPolicies);
    }
    
    renderDetails = async (policyId) => {
        const policy = this.state.policiesList.find(p => p.id === policyId);
        
        let totalCost = 0;
        if (policy.rules && policy.rules.length > 0) {
            policy.rules.forEach(coverage => {
               const {minimumCost, percentage} = coverage;
               const costPercentualApplied = percentage * policy.insuredValue;
               const coverageCost = Math.max(minimumCost, costPercentualApplied);
               totalCost += coverageCost;
            });
        }
        
        const formattedTotalCost = totalCost.toFixed(2);
        
        return `
            <div class="container">
                <div class="row">
                    <div class="col-6"><strong>Número de placa:</strong></div>
                    <div class="col-6">${policy.license}</div>
                </div>
                <div class="row">
                    <div class="col-6"><strong>Fecha de Inicio:</strong></div>
                    <div class="col-6">${policy.initialDate}</div>
                </div>
                <div class="row">
                    <div class="col-6"><strong>Marca del Vehículo:</strong></div>
                    <div class="col-6">${policy.vehicle.brand}</div>
                </div>
                <div class="row">
                    <div class="col-6"><strong>Modelo del Vehículo:</strong></div>
                    <div class="col-6">${policy.vehicle.model}</div>
                </div>
                <div class="row">
                    <div class="col-6"><strong>Valor:</strong></div>
                    <div class="col-6">₡${policy.insuredValue}</div>
                </div>
                <div class="row">
                    <div class="col-6"><strong>Plazo:</strong></div>
                    <div class="col-6">${policy.term}</div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <h5>Coberturas:</h5>
                        <ul>
                            ${policy.rules?.map(coverage => `<li>${coverage.name} - ${coverage.description}.</li>`).join('')}
                        </ul>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <h5>Costo Total:</h5>
                        ₡${formattedTotalCost}
                    </div>
                </div>
            </div>
        `;
    };
    
    showPolicyDetails = async (event) => {
        let target = event.target;
        
        if (target.tagName !== 'BUTTON') {
            target = target.parentElement;
        }
        
        const policyId = target.dataset.id;
        
        const details = await this.renderDetails(policyId);
        
        const modalContent = this.dom.querySelector('#modal-details-content');
        modalContent.innerHTML = details;
        
        this.policyDetailsModal.show();
    }
    
    loadCoverages = async () => {
        const request = new Request(`${backend}/categories`, {
           method:'GET',
           headers: {'Content-Type': 'application/json'}
        });
        
        try {
            const response = await fetch(request);
            if (!response.ok) {
                errorMessage("Falló la conexión con el servidor.");
                return;
            }
            console.log("OK LOADING CATEGORIES");
            const categories = await response.json();
            
            this.state.categories = categories;
            
            const coveragesHTML = this.renderCoverages();
            await this.loadVehicles();
            this.dom.querySelector('#createPolicyModal .modal-body .scrollable').innerHTML = coveragesHTML;
            this.dom.querySelector('#vehicle').innerHTML = this.renderVehicles();
            this.dom.addEventListener('input', this.validateForm);
            this.createPolicyModal.show();
        }catch(err){
            console.error(err);
        }
    }
    
    renderCoverages = () => {
        let html = '';
        this.state.categories.forEach(category => {
           html += `
            <div>
                <h4>${category.name}</h4>
            `;
           category.coverages.forEach(coverage => {
              html += `
                <div class="form-check">
                    <input class="form-check-input coverage-input" type="checkbox" value="" id="${coverage.id}">
                    <label class="form-check-label" for="${coverage.name}">
                        ${coverage.description}
                    </label>
                </div>
               `;
           });
           
           html += `<hr></div>`;
        });
        
        return html;
    }
    
    loadVehicles = async () => {
        const request = new Request(`${backend}/vehicles`, {
            method:'GET',
            headers: {'Content-Type': 'application/json'}
        });
        try {
            const response = await fetch(request);
            if (!response.ok) {
                errorMessage("Falló la conexión con el servidor");
                return;
            }
            
            const vehiclesByBrand = await response.json();
            this.state.vehiclesByBrand = vehiclesByBrand;
        } catch (err) {
            console.error(err);
        }
    }
    
    renderVehicles = () => {
        if (this.state.vehiclesByBrand.length === 0) {
            return `
                <option value="">No hay vehículos registrados en este momento</option>
            `;
        }
        
        let html = '<option value="">Seleccione...</option>';
        this.state.vehiclesByBrand.forEach(brand => {
           html += `<optgroup label="${brand[0].brand}">`;
           brand.forEach(model => {
               html += `<option value="${model.brand}-${model.model}-${model.year}">${model.brand} - ${model.model} - ${model.year}</option>`;
           });
           html += `</optgroup>`;
        });
        return html;
    }
    
    showModal = async () => {
        await this.loadCoverages();
    };
    
    makenew = () => {
        this.state.mode = 'A';
        this.showModal();
    }
    
    renderToast = () => {
        return `
          <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" id="alert" style="position: fixed;top: 50%;left: 50%;transform: translate(-50%, -50%);z-index: 9999;">
            <div class="toast-header">
              <strong class="me-auto">Alerta</strong>
              <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
              Asegurate de llenar todos los datos básicos con valores válidos y seleccionar al menos una cobertura para tu Póliza.
            </div>
          </div>
        `;
    }
    
    validateAndProceed = async () => {
        const AllInputsFilled = Array.from(this.dom.querySelectAll('.required-input')).every(input => input.value.trim() !== '');
        const atLeastOneCoverageSelected = Array.from(this.dom.querySelectorAll('.coverage-input')).some(input => input.checked);
        const insuredValue = this.dom.querySelector('#insuredValue').value.trim(0);
        const validInsuredValue = !isNaN(insuredValue);
        
        if (!AllInputsFilled || !validInsuredValue || !atLeastOneCoverageSelected) {
            const toastElement = new bootstrap.Toast(this.dom.querySelector('#alert'), {
               animation:true,
               delay: 2000
            });
            toastElement.show();
            return;
        }
        
        this.gatherPolicyData();
        this.addPolicy();
    }
    
    gatherPolicyData = () => {
        const license = this.dom.querySelector('#license').value;
        const vehicle = this.dom.querySelector('#vehicle').value.split('-');
        const brand = vehicle[0];
        const model = vehicle[1];
        const year = vehicle[2];
        const insuredValue = this.dom.querySelector('#insuredValue').value;
        
        const termInputs = Array.from(this.dom.querySelectorAll('input[name="termChosen"]'));
        const term = termInputs.find(input => input.checked)?.id || '';
        
        const coverageInputs = Array.from(this.dom.querySelectorAll('.scrollable .form-check-input'));
        const coverageSelec = coverageInputs.filter(input => input.checked).map(input => input.id);
        
        const coverages = coverageSelec.map(sel => this.state.categories.find());
        
        const client = globalstate.client;
    }
    
    addPolicy = () => {
        //
    }
}
