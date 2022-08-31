import { View, Text, TouchableOpacity } from 'react-native'
import React from 'react'
import { TextInput } from 'react-native-gesture-handler'
import { StyleSheet } from 'react-native'
import { useEffect } from 'react'
import { useContext } from 'react'
import { AuthContext } from '../store/AuthContext'


const Comments = ({props, setVisible, navigation}) => {
const authCtx = useContext(AuthContext)
    useEffect(()=>{
        console.log(props)
        console.log(authCtx.teacher)
    })
    function navigate(){
        
    }
  return (
    <View style={{flex:1 }}>
            <TextInput style={styles.textBox} placeholder="Állat neve"/>
            <TouchableOpacity onPress={()=>{navigat()}} style={{
                backgroundColor: '#608d56', borderRadius:2, padding:4, width: 380,
                marginLeft: 15
            }}>
                <Text style={{textAlign: 'center'}}> Submit</Text>
            </TouchableOpacity>
            </View>  
  )
}

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