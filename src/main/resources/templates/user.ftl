<html>
<head>
    <title>用户信息</title>
    <meta charset="UTF-8">
</head>
<body>
<table border="1" align="center" width="50%">
    <tr>
        <th>ID</th>
        <th>名字</th>
        <th>年龄</th>
    </tr>
    <#list list as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.userName}</th>
            <th>${user.age}</th>
        </tr>

    </#list>

</table>


</body>

</html>
