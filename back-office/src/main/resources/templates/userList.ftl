<#import "parts/common.ftl" as c>

<@c.page>
    <a class="btn btn-outline-success mb-4" href="/registration">Добавить пользователя</a>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">User</th>
            <th scope="col">Password</th>
            <th scope="col">Roles</th>
            <th scope="col">ФИО</th>
            <th scope="col">Сhange</th>
        </tr>
        </thead>
        <tbody>
        <#list listUsers as users>
        <form method="post" action="/user/edit/${users.id}" enctype="multipart/form-data">
            <tr>
                <td><input type="text" name="name" value="${users.name}" class="form-control" aria-describedby="button-addon4"></td>
                <td><input type="text" name="password" value="${users.password}" class="form-control" aria-describedby="button-addon4"></td>
                <td>
                    <#list roles as role>
                        <div>
                            <label><input type="checkbox" name="${role}" ${users.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                        </div>
                    </#list>
                </td>
                <td>
                    <div class="input-group mb-3">
                        <select class="custom-select" name="idUseEmp">
                            <#if users.idUseEmp??>
                                <option value="${users.idUseEmp.id}">${users.idUseEmp.fullName}</option>
                                <#list employee?sort_by("fullName") as emp>
                                    <#if emp.id != users.idUseEmp.id>
                                        <option value="${emp.id}">${emp.fullName}</option>
                                    </#if>
                                </#list>
                            <#else>
                                <option value=""></option>
                                <#list employee?sort_by("fullName") as emp>
                                        <option value="${emp.id}">${emp.fullName}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                </td>
                <td><div class="form-group mb-5">
                        <button type="submit" class="btn btn-outline-success">Сохранить</button>
                        <a class="btn btn-outline-danger" href="/user/delete/${users.id}">Удалить</a>
                    </div>
                </td>
            </tr>
            <input type="hidden" value="${users.id}" name="userId">
<#--            <input type="hidden" value="${_csrf.token}" name="_csrf">-->
        </form>
        </#list>
        </tbody>
    </table>
</@c.page>