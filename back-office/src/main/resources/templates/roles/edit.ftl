
<#import "../parts/common.ftl" as c>

<@c.page>
    <h4>Редактирование:</h4>

    <form action="/roles/edit" method="post">
        <div class="input-group mt-2">
            <input type="text" name="name" value="${roles.name}" class="form-control" aria-describedby="button-addon4">
            <div class="input-group-append" id="button-addon4">
                <button class="btn btn-outline-success" type="submit">Сохранить</button>
                <a class="btn btn-outline-danger" href="/roles/delete/${roles.idRole}">Удалить</a>
            </div>
        </div>
        <input type="hidden" value="${roles.idRole}" name="rolesId">
<#--        <input type="hidden" value="${_csrf.token}" name="_csrf">-->
    </form>

        <div class="mt-4 mb-2">Список доступных сервисов у роли ${roles.name}:</div>
        <#list idRolSers?sort_by("name") as idRolSer>
            <li class="input-group mb-3">
                <div class="input-group-prepend">
                    <a class="btn btn-outline-danger" id="basic-addon1" href="/roles/removeServices/${roles.idRole}/${idRolSer.getIdServices()}">x</a>
                </div>
                <div class="form-control" aria-describedby="basic-addon1">${idRolSer.getName()}</div>
            </li>
        </#list>

    <div class="btn-group dropright">
        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Добавить
        </button>
        <div class="dropdown-menu">
            <#list services?sort_by("name") as service>
                    <a class="dropdown-item" href="/roles/addServices/${roles.idRole}/${service.idServices}">${service.name}</a>
            </#list>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/services">Создать сервис</a>
        </div>
        </div>
    </div>

</@c.page>