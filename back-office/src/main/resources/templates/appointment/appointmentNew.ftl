
<a class="btn btn-outline-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Новое назначение
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="date" class="form-control"
                       value="<#if appointments??>${appointments.dateApp}</#if>" name="dateApp" placeholder="Введите дату">
            </div>
            <div class="input-group mb-3">
                <select class="custom-select" name="idAppEmp">
                    <option value="select">Выберите сотрудника ...</option>
                    <#list employee?sort_by("fullName") as emp>
                        <option value="${emp.id}">${emp.fullName}</option>
                    </#list>
                </select>
            </div>
            <div class="input-group mb-3">
                <select class="custom-select" name="idAppPos">
                    <option value="select">Выберите должность ...</option>
                    <#list position?sort_by("name") as pos>
                        <option value="${pos.id}">${pos.name}</option>
                    </#list>
                </select>
            </div>
            <div class="input-group mb-3">
                <select class="custom-select" name="idAppDep">
                    <option value="select">Выберите департамент ...</option>
                    <#list department?sort_by("name") as dep>
                        <option value="${dep.id}">${dep.name}</option>
                    </#list>
                </select>
            </div>

<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <input type="hidden" name="id" value="<#if appointments??>${appointments.id}</#if>" />
            <div class="form-group mb-5">
                <button type="submit" class="btn btn-outline-success">Сохранить</button>
            </div>
        </form>
    </div>
</div>







