package com.sibilantsolutions.utils.util;

import static org.junit.Assert.assertEquals;

import java.nio.ByteOrder;

import org.junit.Test;

public class ConvertTest
{

    @Test
    public void testToNum()
    {
        assertEquals( 0, Convert.toNum( new byte[]{}, 0, 0 ) );
        assertEquals( 0, Convert.toNum( new byte[]{ 0 }, 0, 0 ) );
        assertEquals( 0, Convert.toNum( new byte[]{ 0 }, 0, 1 ) );

        assertEquals( 0, Convert.toNum( new byte[]{ 1 }, 0, 0 ) );
        assertEquals( 1, Convert.toNum( new byte[]{ 1 }, 0, 1 ) );

        assertEquals( 255, Convert.toNum( new byte[]{ (byte)0xFF, (byte)0xFF }, 0, 1 ) );
        assertEquals( 65535, Convert.toNum( new byte[]{ (byte)0xFF, (byte)0xFF }, 0, 2 ) );

        assertEquals( 255, Convert.toNum( new byte[]{ 0, 0, (byte)0xFF, (byte)0xFF }, 2, 1 ) );
        assertEquals( 65535, Convert.toNum( new byte[]{ 0, 0, (byte)0xFF, (byte)0xFF }, 2, 2 ) );


        assertEquals( 18, Convert.toNum( new byte[]{ 0, 0, (byte)0x12, (byte)0x34, 0, 0 }, 2, 1 ) );
        assertEquals( 4660, Convert.toNum( new byte[]{ 0, 0, (byte)0x12, (byte)0x34, 0, 0 }, 2, 2 ) );


        assertEquals( 18, Convert.toNum( new byte[]{ 0, 0, (byte)0x12, (byte)0x34, 0, 0 }, 2, 1, ByteOrder.LITTLE_ENDIAN ) );
        assertEquals( 13330, Convert.toNum( new byte[]{ 0, 0, (byte)0x12, (byte)0x34, 0, 0 }, 2, 2, ByteOrder.LITTLE_ENDIAN ) );
	
	assertEquals( 0x003D918, Convert.toNum( "" + (char)0x00 + (char)0x03 + (char)0xD9 + (char)0x18 ) );
    }

       @Test
    public void testToBigEndian()
    {
        assertEquals( "" + (char)0x00, Convert.toBigEndian( 0 ) );
        assertEquals( "" + (char)0x01, Convert.toBigEndian( 1 ) );
        assertEquals( "" + (char)0xFF, Convert.toBigEndian( 255 ) );
        assertEquals( "" + (char)0x01 + (char)0x00, Convert.toBigEndian( 256 ) );
        assertEquals( "" + (char)0x01 + (char)0xFF, Convert.toBigEndian( 511 ) );

    }

    @Test
    public void testToBigEndianLongInt()
    {
        assertEquals( "" + (char)0x01, Convert.toBigEndian( 1, 1 ) );
        assertEquals( "" + (char)0xFF, Convert.toBigEndian( 255, 1 ) );

        assertEquals( "" + (char)0x00 + (char)0x00, Convert.toBigEndian( 0, 2 ) );
        assertEquals( "" + (char)0x00 + (char)0x01, Convert.toBigEndian( 1, 2 ) );
        assertEquals( "" + (char)0x00 + (char)0xFF, Convert.toBigEndian( 255, 2 ) );
        assertEquals( "" + (char)0x01 + (char)0x00, Convert.toBigEndian( 256, 2 ) );
        assertEquals( "" + (char)0x01 + (char)0xFF, Convert.toBigEndian( 511, 2 ) );

        assertEquals( "" + (char)0x00 + (char)0x00 + (char)0x00 + (char)0x00, Convert.toBigEndian( 0, 4 ) );
        assertEquals( "" + (char)0x00 + (char)0x00 + (char)0x00 + (char)0x01, Convert.toBigEndian( 1, 4 ) );
        assertEquals( "" + (char)0x00 + (char)0x00 + (char)0x00 + (char)0xFF, Convert.toBigEndian( 255, 4 ) );
        assertEquals( "" + (char)0x00 + (char)0x00 + (char)0x01 + (char)0x00, Convert.toBigEndian( 256, 4 ) );
        assertEquals( "" + (char)0x00 + (char)0x00 + (char)0x01 + (char)0xFF, Convert.toBigEndian( 511, 4 ) );
    }

    @Test
    public void testToLittleEndianLong()
    {
        assertEquals( "" + (char)0x00, Convert.toLittleEndian( 0 ) );
        assertEquals( "" + (char)0x01, Convert.toLittleEndian( 1 ) );
        assertEquals( "" + (char)0xFF, Convert.toLittleEndian( 255 ) );
        assertEquals( "" + (char)0x00 + (char)0x01, Convert.toLittleEndian( 256 ) );
        assertEquals( "" + (char)0xFF + (char)0x01, Convert.toLittleEndian( 511 ) );
    }

    @Test
    public void testToLittleEndianLongInt()
    {
        assertEquals( "" + (char)0x01, Convert.toLittleEndian( 1, 1 ) );
        assertEquals( "" + (char)0xFF, Convert.toLittleEndian( 255, 1 ) );

        assertEquals( "" + (char)0x00 + (char)0x00, Convert.toLittleEndian( 0, 2 ) );
        assertEquals( "" + (char)0x01 + (char)0x00, Convert.toLittleEndian( 1, 2 ) );
        assertEquals( "" + (char)0xFF + (char)0x00, Convert.toLittleEndian( 255, 2 ) );
        assertEquals( "" + (char)0x00 + (char)0x01, Convert.toLittleEndian( 256, 2 ) );
        assertEquals( "" + (char)0xFF + (char)0x01, Convert.toLittleEndian( 511, 2 ) );

        assertEquals( "" + (char)0x00 + (char)0x00 + (char)0x00 + (char)0x00, Convert.toLittleEndian( 0, 4 ) );
        assertEquals( "" + (char)0x01 + (char)0x00 + (char)0x00 + (char)0x00, Convert.toLittleEndian( 1, 4 ) );
        assertEquals( "" + (char)0xFF + (char)0x00 + (char)0x00 + (char)0x00, Convert.toLittleEndian( 255, 4 ) );
        assertEquals( "" + (char)0x00 + (char)0x01 + (char)0x00 + (char)0x00, Convert.toLittleEndian( 256, 4 ) );
        assertEquals( "" + (char)0xFF + (char)0x01 + (char)0x00 + (char)0x00, Convert.toLittleEndian( 511, 4 ) );
    }

    @Test
    public void testToNumLittleEndian()
    {
        assertEquals( 0x003D918, Convert.toNumLittleEndian( "" + (char)0x18 + (char)0xD9 + (char)0x03 + (char)0x00 ) );
    }

    @Test
    public void testPadRear()
    {
        assertEquals( "abcZZZ", Convert.padRear( "abc", 6, 'Z' ) );
    }
    
}
