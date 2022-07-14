import { View, Text, StyleSheet } from 'react-native'
import React from 'react'
import SelectDropdown from 'react-native-select-dropdown'
import { Picker } from 'react-native-actions-sheet-picker'

const Dropdown = ({props,label}) =>{ 
    let x = []
    if(label == "Subject"){
        x = props.map((y)=>{return y.subjectname})
    } else if(label == "Parent"){
        x = props.map((y)=>{return y.firstname + " " + y.lastname})
    }
    
  return (
    <View style={styles.background}>
     <SelectDropdown defaultButtonText= {"Select " + label} data={x} search={true} />
    </View>
  )
}


export default Dropdown;


const styles = StyleSheet.create({
    background:{
        color: 'black',
    } ,
     label: {
        fontSize:12,
        marginBottom: 4,
        fontSize: 18,
        alignContent: 'center',
        alignItems:'center'}
})