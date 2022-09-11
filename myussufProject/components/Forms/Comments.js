import { View, Text, TouchableOpacity } from 'react-native'
import React from 'react'
import { TextInput } from 'react-native-gesture-handler'
import { StyleSheet } from 'react-native'
import { useEffect } from 'react'
import { useContext } from 'react'
import { AuthContext } from '../store/AuthContext'
import { sentComment } from '../util/http'
import { useState } from 'react'


const Comments = ({props, setVisible, navigation}) => {
const authCtx = useContext(AuthContext)
const [comment, setComments] = useState("");
    
useEffect(()=>{
        console.log(props[0].id)
        console.log(authCtx.teacherinfo)
    },[])

    async function setComment(studentid, teacherid,message){
       const response = await sentComment(studentid, teacherid,message).catch(err=>console.log(err))
    }
 
  return (
    <View style={{display: 'flex',
     }}>
            <TextInput style={styles.textBox} placeholder="Leave Comment" onChangeText={(x)=>{setComments(x)}}/>
            <TouchableOpacity onPress={()=>{setComment(props[0].id,authCtx.teacherinfo.id,comment), setVisible(false)}} style={{
                backgroundColor: '#608d56', margin: 5,  borderRadius: 2, width: 380, marginLeft: 15}}>
                <Text style={{textAlign: 'center'}}> Submit</Text>
            </TouchableOpacity>
            <TouchableOpacity onPress={()=>{setVisible(false)}} style={{
                backgroundColor: '#608d56', margin: 5, width: 380, borderRadius: 2, marginLeft: 15}}>
                <Text style={{textAlign: 'center'}}> Cancel</Text>
            </TouchableOpacity>
            </View>  
  )
}

//                 marginLeft: 15
export default Comments

const styles = StyleSheet.create({
    textBox:{
        borderWidth:2,
        borderColor: 'gray',
        margin:15,
        borderRadius:10,
        padding: 10,
        height: 100
    },
})