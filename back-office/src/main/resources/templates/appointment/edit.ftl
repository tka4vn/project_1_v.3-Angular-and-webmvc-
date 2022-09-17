<#import "../parts/common.ftl" as c>

<@c.page>
    <h4>Редактирование:</h4>
    <div class="form-group mt-3">
        <form method="post" action="/appointment/edit" enctype="multipart/form-data">
            <div class="form-group">
                <input type="date" class="form-control" value="<#if appointment.dateApp??>${appointment.dateApp}</#if>" name="dateApp">
            </div>
            <div class="input-group mb-3">
                <select class="custom-select" name="idAppEmp">
                    <option value="${appointment.idAppEmp.id}">${appointment.idAppEmp.fullName}</option>
                    <#list employee?sort_by("fullName") as emp>
                        <#if emp.id != appointment.idAppEmp.id>
                            <option value="${emp.id}">${emp.fullName}</option>
                        </#if>
                    </#list>
                </select>
            </div>
            <div class="input-group mb-3">
                <select class="custom-select" name="idAppPos">
                    <option value="${appointment.idAppPos.id}">${appointment.idAppPos.name}</option>
                    <#list position?sort_by("name") as pos>
                        <#if pos.id != appointment.idAppPos.id>
                            <option value="${pos.id}">${pos.name}</option>
                        </#if>
                    </#list>
                </select>
            </div>
            <div class="input-group mb-3">
                <select class="custom-select" name="idAppDep">
                    <option value="${appointment.idAppDep.id}">${appointment.idAppDep.name}</option>
                    <#list department?sort_by("name") as dep>
                        <#if dep.id != appointment.idAppDep.id>
                            <option value="${dep.id}">${dep.name}</option>
                           </#if>
                    </#list>
                </select>
            </div>

<#--            <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
            <input type="hidden" value="${appointment.id}" name="appointmentId">
            <div class="form-group mb-5">
                <button type="submit" class="btn btn-success">Сохранить</button>
                <a class="btn btn-outline-danger" href="/appointment/delete/${appointment.id}">Удалить</a>
            </div>
        </form>
    </div>

</@c.page>