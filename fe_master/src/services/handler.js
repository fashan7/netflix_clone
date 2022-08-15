export default async function(response) {
	let responseType = response.headers.get('content-type')
	let data = null;
	if(responseType && responseType.indexOf('application/json') > -1) {
		let t = await response.text();
		try {
			data = JSON.parse(t);
		}
		catch {
			data = t;
		}
	}
	else {
		data = await response.text();
	}
	if(response.ok) {
		if(data) return data;
		else return response;
	}
}