<#include "../parts/security.ftl">

<div class="card-columns">
    <#list request?sort_by("dateReq") as req>
        <div class="card my-3">
            <div class="card-header text-muted tools">
                Пользователь: ${req.idReqUse.name}
                <#if isAdmin><span style="float: right;"><a href="/request/delete/${req.id}" style="color: #e11c1c">х</a></span></#if>
            </div>
            <div class="m-2">
                <span>Дата: </span><i>${req.dateReq}</i><br/>
                <span>Сервис: </span><i>${req.idReqSer.name}</i><br/>
                <span>Роль: </span><i>${req.idReqRol.name}</i><br/>
                <span>Статус: </span><i>${req.idReqSta.name}</i>
            </div>
            <div class="card-footer text-muted tools" style="height: 65px;">
            <div class="row justify-content-end">
                <div class="col-auto">

                <#--Логика для владельца-->
                    <#list req.idReqSer.idSerIng as x>
                        <#if x.idIngUse.id == currentUserId && x.idIngRol.name == "Владелец">

                            <#if req.idReqRol.name == "Администратор"> <#--Если хотят стать админом владелец сам открывает доступ-->
                                <#if req.idReqSta.idStatus lt 25> <#-- Скрыть кнопку если дуступ одобрен -->
                                    <a class="btn btn-outline-success"
                                       href="/request/ingressNewAdmin/
                                       ${req.idReqUse.id}/${req.idReqSer.idServices}/${req.idReqRol.idRole}/${req.id}/25
                                        ">Одобрить Админа</a>
                                </#if>
                            <#else>
                                <#if req.idReqSta.idStatus lt 25> <#-- Скрыть кнопку если дуступ одобрен -->
                                    <a class="btn btn-outline-success" href="/request/ingressNew/${req.id}/26">Одобрить</a>
                                </#if>
                            </#if>
                        <#-- Отказать в доступе и удалить из ingress если есть-->
                            <#list req.idReqSer.idSerIng as x>
                                <#if x.idIngUse.id == req.idReqUse.id && x.idIngSer.idServices == req.idReqSer.idServices && x.idIngRol.idRole == req.idReqRol.idRole>
                                    <a class="btn btn-outline-danger" href="/request/ingressRemove/${req.id}/23/${x.idIngress}">Отказать</a>
                                </#if>
                            </#list>
                        <#-- Отказать в доступе-->
<#--                            <#if req.idReqSta.name != "Доступ открыт">-->
<#--                                <a class="btn btn-outline-danger" href="/request/ingressNew/${req.id}/23">Отказать</a>-->
<#--                            </#if>-->
                        </#if>
                    </#list>

                <#--Логика для администратора-->
                    <#list req.idReqSer.idSerIng as x>
                        <#if x.idIngUse.id == currentUserId && x.idIngRol.name == "Администратор" &&
                            req.idReqRol.name != "Администратор" && req.idReqRol.name != "Владелец" &&
                            req.idReqSta.name != "Новая" && req.idReqSta.name != "Отказано владельцем">
                        <#-- Скрыть кнопку если дуступ одобрен -->
                            <#if req.idReqSta.idStatus != 25>
                                <a class="btn btn-outline-success"
                                   href="/request/ingressNewAdmin/
                                       ${req.idReqUse.id}/${req.idReqSer.idServices}/${req.idReqRol.idRole}/${req.id}/25
                                        ">Одобрить</a>
                            </#if>
                        <#-- Отказать в доступе и удалить из ingress если есть-->
                            <#list req.idReqSer.idSerIng as x>
                                <#if x.idIngUse.id == req.idReqUse.id && x.idIngSer.idServices == req.idReqSer.idServices && x.idIngRol.idRole == req.idReqRol.idRole>
                                    <a class="btn btn-outline-danger" href="/request/ingressRemove/${req.id}/24/${x.idIngress}">Отказать</a>
                                </#if>
                            </#list>
                        <#-- Отказать в доступе-->
                            <#if req.idReqRol.name != "Администратор" && req.idReqSta.name != "Доступ открыт">
                                <a class="btn btn-outline-danger" href="/request/ingressNew/${req.id}/24">Отказать</a>
                            </#if>
                        </#if>
                    </#list>
                </div>
            </div>
            </div>
        </div>
    </#list>
</div>