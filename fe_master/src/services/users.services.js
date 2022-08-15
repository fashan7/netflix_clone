import axios from 'axios'

export const postUserData = async(value) => {
	try {
        const header = {
            'Content-Type': 'application/json',
        }
        await axios.post(`/jav/users`, value, {header});
        // await axios({method:'post',url:`/jav/users`,data:value,headers:header})
    } catch (e) {
        throw e;
    }
}