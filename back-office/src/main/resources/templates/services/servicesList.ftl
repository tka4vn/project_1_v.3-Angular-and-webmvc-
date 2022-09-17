<#include "../parts/security.ftl">

<div class="row row-cols-1 row-cols-md-3 g-4">
    <#list services?sort_by("name") as service>
    <div class="col-4 mt-4">
        <div class="card h-100">
            <div class="card-header">
                <div class="card-title text-end">
                    <strong>${service.name}</strong>
                </div>
            </div>
            <div class="card-body">
                <#list service.idSerRol?sort_by("name") as idSerRol>
                    <div class="mb-3">
                    <span class="ml-2 mr-3">${idSerRol.name}</span>
                    <#list service.idSerIng as idSerIng>
                        <#if idSerIng.idIngRol.name == idSerRol.name>
                            <span class="text-secondary"><i>${idSerIng.idIngUse.name}</i></span>
                        </#if>
                    </#list>
                    </div>
                <#else>
                    <meta http-equiv="Refresh" content="0; /services/new/${currentUserId}/${service.idServices}/23">
                </#list>
            </div>
            <div class="card-footer" style="height: 50px;">
                <small class="text-muted">
                        <#list service.idSerIng as x>
                            <#if x.idIngUse.id == currentUserId>
                                ${x.idIngRol.name}<#sep>,
                            </#if>
                            <#if x.idIngUse.id == currentUserId && x.idIngRol.name == "Владелец">
                                <a href="/services/${service.idServices}"><span> | Редактировать</span></a>
                            </#if>
                        </#list>
                </small>
            </div>
        </div>
    </div>
    </#list>
</div>
