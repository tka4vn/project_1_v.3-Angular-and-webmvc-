<#macro registration path>
<form action="${path}" method="post">
    <h5 class="mb-4">Регистрация пользователя:</h5>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input type="text" name="name" class="form-control" placeholder="User name" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
        </div>
    </div>
<#--    <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
    <button class="btn btn-primary" type="submit">Сохранить</button>
</form>
</#macro>

<#macro login path>
    <h5 class="mb-4">Авторизируйтесь:</h5>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Имя пользователя:</label>
            <div class="col-sm-6">
<#--                <input type="text" name="username" class="form-control" placeholder="User name" />-->
                <select class="custom-select" name="username">
                    <#list users?sort_by("name") as user>
                        <option value="${user.name}">${user.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
        <div class="form-group mt-4">
            <button class="btn btn-outline-primary" type="submit">Вход</button>
            <a class="btn btn-outline-success" href="/registration">Регистрация</a>
        </div>
    </form>
</#macro>