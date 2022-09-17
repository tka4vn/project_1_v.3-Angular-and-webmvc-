
<#import "../parts/common.ftl" as c>

<@c.page>
    <h4>Редактирование:</h4>
    <form action="/position/edit" method="post">
        <div class="input-group mt-2">
            <input type="text" name="name" value="${position.name}" class="form-control" aria-describedby="button-addon4">
            <div class="input-group-append" id="button-addon4">
                <button class="btn btn-outline-success" type="submit">Сохранить</button>
                <a class="btn btn-outline-danger" href="/position/delete/${position.id}">Удалить</a>
            </div>
        </div>
        <input type="hidden" value="${position.id}" name="positionId">
<#--        <input type="hidden" value="${_csrf.token}" name="_csrf">-->
    </form>


</@c.page>