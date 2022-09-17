<#include "../parts/security.ftl">

<div class="card-columns">
    <#list appointment as app>
        <div class="card my-3">
            <div class="card-header text-muted tools">
                <h6 class="card-title text-end">${app.idAppEmp.fullName}</h6>
            </div>
            <div class="m-2">
                <span>Дата: </span><i><#if app.dateApp??>${app.dateApp}</#if></i><br/>
                <span>Должность: </span><i>${app.idAppPos.name}</i><br/>
                <span>Департамент: </span><i>${app.idAppDep.name}</i><br/>
            </div>
            <div class="card-footer text-muted tools">
            <div class="row justify-content-end">
                <div class="col-auto">
                    <small class="text-muted">
                        <a href="/appointment/${app.id}"><span>Edit</span></a>
                    </small>
                </div>
            </div>
            </div>
        </div>
    </#list>
</div>