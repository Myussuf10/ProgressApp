import { View, Text, StyleSheet } from 'react-native'
import React from 'react'
import SelectDropdown from 'react-native-select-dropdown'
import { Picker } from 'react-native-actions-sheet-picker'

const Dropdown = ({props,label}) =>{ 
    const x = props.map((y)=>{return y.firstname + " " + y.lastname})
  return (
    <View style={styles.background}>
    <Text style={styles.label}>{label}</Text>
     <SelectDropdown color='green' data={x} search={true} />
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