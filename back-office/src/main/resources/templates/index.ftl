<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#include "parts/security.ftl">

<@c.page>
<#if user??>
    <h5 class="mb-3">Здравствуйте,
        <#if user.idUseEmp??>
            <#list users as user>
                <#if user.id == currentUserId>
                    ${user.idUseEmp.fullName}
                </#if>
            </#list>
        <#else>
            <i>необходимо подключить сотрудника</i>
        </#if>
    </h5>
    <div class="text-secondary mb-2"><strong>Должность: </strong>
        <#if user.idUseEmp??>
            <#list users as user>
                <#if user.id == currentUserId>
                    <#list user.idUseEmp.idEmpApp as app>
                        <i>${app.idAppPos.name}</i>
                    </#list>
                </#if>
            </#list>
        <#else>
            <i>необходимо подключить сотрудника</i>
        </#if>
    </div>
    <div class="text-secondary mb-3"><strong>Департамент: </strong>
        <#if user.idUseEmp??>
            <#list users as user>
                <#if user.id == currentUserId>
                    <#list user.idUseEmp.idEmpApp as app>
                        <i>${app.idAppDep.name}</i>
                    </#list>
                </#if>
            </#list>
        <#else>
            <i>необходимо подключить сотрудника</i>
        </#if>
    </div>

    <hr>
    <div class="mb-2 mt-4 text-secondary"><b>Доступные сервисы: </b></div>
    <div class="card-columns ml-5">
        <div class="card my-3">
            <#list services?sort_by("name") as service>
                <#assign s>
                    <#list service.idSerIng as x>
                        <#if x.idIngUse.id == currentUserId>
                            ${x.idIngRol.name}<#sep>,
                        </#if>
                    </#list>
                </#assign>
                <#if s != "">
                    <div class="ml-5 m-1 text-secondary">${service.name}: <i>${s}</i></div>
                </#if>
            </#list>
        </div>
    </div>
    <hr>
    <div class="mb-2 text-secondary"><b>Ваши заявки на рассмотрении: </b></div>
    <div class="card-columns ml-5">
        <#list request?sort_by("dateReq") as req>
            <#if req.idReqUse.id == currentUserId && req.idReqSta.name != "Доступ открыт">
                <div class="card my-3">
                    <div class="card-header text-primary">
                        <span>Статус: </span><i>${req.idReqSta.name}</i>
                    </div>
                    <div class="m-2 ml-3 text-secondary">
                        <span>Сервис: </span><i>${req.idReqSer.name}</i><br/>
                        <span>Роль: </span><i>${req.idReqRol.name}</i><br/>
                        <span>Дата: </span><i>${req.dateReq}</i><br/>
                    </div>
                </div>
            </#if>
        </#list>
    </div>
    <hr>
    <div class="mb-2 text-secondary"><b>Ваши заявки требующие одобрения:</b></div>
    <div class="card-columns ml-5">
    <#list request?sort_by("dateReq") as req>
        <#assign zList>
            <div class="card my-3">
                <div class="card-header text-success ">
                    <span>Статус: </span><i>${req.idReqSta.name}</i>
                </div>
                <div class="m-2 ml-3 text-secondary">
                    <span>Сервис: </span><i>${req.idReqSer.name}</i><br/>
                    <span>Пользователь: </span><i>${req.idReqUse.name}</i><br/>
                    <span>Роль: </span><i>${req.idReqRol.name}</i><br/>
                    <span>Дата: </span><i>${req.dateReq}</i><br/>
                </div>
            </div>
        </#assign>
        <#assign z>
            <#list req.idReqSer.idSerIng as x>
                <#if x.idIngUse.id == currentUserId && x.idIngRol.name == "Владелец">
                    <#if req.idReqRol.name == "Администратор">
                        <#if req.idReqSta.idStatus lt 25>${zList}</#if>
                    <#else>
                        <#if req.idReqSta.idStatus lt 25>${zList}</#if>
                    </#if>
                </#if>
                <#if x.idIngUse.id == currentUserId && x.idIngRol.name == "Администратор" &&
                req.idReqRol.name != "Администратор" && req.idReqRol.name != "Владелец" &&
                req.idReqSta.name != "Новая" && req.idReqSta.name != "Отказано владельцем">
                    <#if req.idReqSta.idStatus != 25>${zList}</#if>
                </#if>
            </#list>
        </#assign>
        <#if z != "">
                ${z}
        </#if>
    </#list>
    </div>
    <hr>
<#else>
    <@l.login "/login" />
</#if>
</@c.page>