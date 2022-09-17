<div class="card-columns">
    <#list roles?sort_by("name") as role>
        <div class="card my-3">
            <div class="m-2">
                <a href="/roles/${role.idRole}"><span>${role.name}</span></a><br/>
            </div>
        </div>
    <#else>
        No roles
    </#list>
</div>