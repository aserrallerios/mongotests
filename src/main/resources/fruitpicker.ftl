<html>
	<head>Fruit Picker</head>
	<body>
		<h1>Hello ${name}</h1>
		<p>What is your favourite fruit?</p>
		<form action="/fruit_picker" method="POST">
			<#list fruits as fruit>
				<p>
					<input type="radio" name="fruit" value="${fruit}">${fruit}</input>
				</p>
			</#list>
			<input type="submit" value="Submit"/>
		</form>
	</body>
</html>