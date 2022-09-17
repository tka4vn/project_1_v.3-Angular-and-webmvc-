
<#import "../parts/common.ftl" as c>

<@c.page>
    <h4>Редактирование:</h4>

    <form action="/services/edit" method="post">
        <div class="input-group mt-2">
            <input type="text" name="name" value="${services.name}" class="form-control" aria-describedby="button-addon4">
            <div class="input-group-append" id="button-addon4">
                <button class="btn btn-outline-success" type="submit">Сохранить</button>
                <a class="btn btn-outline-danger" href="/services/delete/${services.idServices}">Удалить</a>
            </div>
        </div>
        <input type="hidden" value="${services.idServices}" name="servicesId">
<#--        <input type="hidden" value="${_csrf.token}" name="_csrf">-->
    </form>

        <div class="mt-4 mb-2">Список доступных ролей у сервиса ${services.name}:</div>
        <#list idSerRols?sort_by("name") as idSerRol>
            <li class="input-group mb-3">
                <div class="input-group-prepend">
                    <#if idSerRol.getName() == "Владелец">
                        <a class="btn btn-outline-success" id="basic-addon1">x</a>
                    <#else>
                        <a class="btn btn-outline-danger" id="basic-addon1" href="/services/removeRoles/${services.idServices}/${idSerRol.getIdRole()}">x</a>
                    </#if>
                </div>
                <div class="form-control" aria-describedby="basic-addon1">
                    ${idSerRol.getName()}:
                    <#list idSerIngs as idSerIng>
                        <#if idSerRol.getName() == idSerIng.idIngRol.name>
                            <i>${idSerIng.idIngUse.name}<#sep>,</i>
                        </#if>
                    </#list>
                </div>
            </li>
        </#list>

    <div class="btn-group dropright">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Добавить
        </button>
        <div class="dropdown-menu">
            <#list roles?sort_by("name") as role>
                    <a class="dropdown-item" href="/services/addRoles/${services.idServices}/${role.idRole}">${role.name}</a>
            </#list>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/roles">Создать роль</a>
        </div>
        </div>
    </div>

</@c.page>