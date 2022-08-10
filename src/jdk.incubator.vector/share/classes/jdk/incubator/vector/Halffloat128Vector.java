/*
 * Copyright (c) 2017, 2022, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package jdk.incubator.vector;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.IntUnaryOperator;

import jdk.incubator.foreign.MemorySegment;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.vector.VectorSupport;

import static jdk.internal.vm.vector.VectorSupport.*;

import static jdk.incubator.vector.VectorOperators.*;

// -- This file was mechanically generated: Do not edit! -- //

@SuppressWarnings("cast")  // warning: redundant cast
final class Halffloat128Vector extends HalffloatVector {
    static final HalffloatSpecies VSPECIES =
        (HalffloatSpecies) HalffloatVector.SPECIES_128;

    static final VectorShape VSHAPE =
        VSPECIES.vectorShape();

    static final Class<Halffloat128Vector> VCLASS = Halffloat128Vector.class;

    static final int VSIZE = VSPECIES.vectorBitSize();

    static final int VLENGTH = VSPECIES.laneCount(); // used by the JVM

    static final Class<Halffloat> ETYPE = Halffloat.class; // used by the JVM

    Halffloat128Vector(short[] v) {
        super(v);
    }

    // For compatibility as Halffloat128Vector::new,
    // stored into species.vectorFactory.
    Halffloat128Vector(Object v) {
        this((short[]) v);
    }

    static final Halffloat128Vector ZERO = new Halffloat128Vector(new short[VLENGTH]);
    static final Halffloat128Vector IOTA = new Halffloat128Vector(VSPECIES.iotaArray());

    static {
        // Warm up a few species caches.
        // If we do this too much we will
        // get NPEs from bootstrap circularity.
        VSPECIES.dummyVector();
        VSPECIES.withLanes(LaneType.BYTE);
    }

    // Specialized extractors

    @ForceInline
    final @Override
    public HalffloatSpecies vspecies() {
        // ISSUE:  This should probably be a @Stable
        // field inside AbstractVector, rather than
        // a megamorphic method.
        return VSPECIES;
    }

    @ForceInline
    @Override
    public final Class<Halffloat> elementType() { return Halffloat.class; }

    @ForceInline
    @Override
    public final int elementSize() { return Halffloat.SIZE; }

    @ForceInline
    @Override
    public final VectorShape shape() { return VSHAPE; }

    @ForceInline
    @Override
    public final int length() { return VLENGTH; }

    @ForceInline
    @Override
    public final int bitSize() { return VSIZE; }

    @ForceInline
    @Override
    public final int byteSize() { return VSIZE / Byte.SIZE; }

    /*package-private*/
    @ForceInline
    final @Override
    short[] vec() {
        return (short[])getPayload();
    }

    // Virtualized constructors

    @Override
    @ForceInline
    public final Halffloat128Vector broadcast(short e) {
        return (Halffloat128Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    public final Halffloat128Vector broadcast(long e) {
        return (Halffloat128Vector) super.broadcastTemplate(e);  // specialize
    }

    @Override
    @ForceInline
    Halffloat128Mask maskFromArray(boolean[] bits) {
        return new Halffloat128Mask(bits);
    }

    @Override
    @ForceInline
    Halffloat128Shuffle iotaShuffle() { return Halffloat128Shuffle.IOTA; }

    @ForceInline
    Halffloat128Shuffle iotaShuffle(int start, int step, boolean wrap) {
      if (wrap) {
        return (Halffloat128Shuffle)VectorSupport.shuffleIota(ETYPE, Halffloat128Shuffle.class, VSPECIES, VLENGTH, start, step, 1,
                (l, lstart, lstep, s) -> s.shuffleFromOp(i -> (VectorIntrinsics.wrapToRange(i*lstep + lstart, l))));
      } else {
        return (Halffloat128Shuffle)VectorSupport.shuffleIota(ETYPE, Halffloat128Shuffle.class, VSPECIES, VLENGTH, start, step, 0,
                (l, lstart, lstep, s) -> s.shuffleFromOp(i -> (i*lstep + lstart)));
      }
    }

    @Override
    @ForceInline
    Halffloat128Shuffle shuffleFromBytes(byte[] reorder) { return new Halffloat128Shuffle(reorder); }

    @Override
    @ForceInline
    Halffloat128Shuffle shuffleFromArray(int[] indexes, int i) { return new Halffloat128Shuffle(indexes, i); }

    @Override
    @ForceInline
    Halffloat128Shuffle shuffleFromOp(IntUnaryOperator fn) { return new Halffloat128Shuffle(fn); }

    // Make a vector of the same species but the given elements:
    @ForceInline
    final @Override
    Halffloat128Vector vectorFactory(short[] vec) {
        return new Halffloat128Vector(vec);
    }

    @ForceInline
    final @Override
    Byte128Vector asByteVectorRaw() {
        return (Byte128Vector) super.asByteVectorRawTemplate();  // specialize
    }

    @ForceInline
    final @Override
    AbstractVector<?> asVectorRaw(LaneType laneType) {
        return super.asVectorRawTemplate(laneType);  // specialize
    }

    // Unary operator

    @ForceInline
    final @Override
    Halffloat128Vector uOp(FUnOp f) {
        return (Halffloat128Vector) super.uOpTemplate(f);  // specialize
    }

    @ForceInline
    final @Override
    Halffloat128Vector uOp(VectorMask<Halffloat> m, FUnOp f) {
        return (Halffloat128Vector)
            super.uOpTemplate((Halffloat128Mask)m, f);  // specialize
    }

    // Binary operator

    @ForceInline
    final @Override
    Halffloat128Vector bOp(Vector<Halffloat> v, FBinOp f) {
        return (Halffloat128Vector) super.bOpTemplate((Halffloat128Vector)v, f);  // specialize
    }

    @ForceInline
    final @Override
    Halffloat128Vector bOp(Vector<Halffloat> v,
                     VectorMask<Halffloat> m, FBinOp f) {
        return (Halffloat128Vector)
            super.bOpTemplate((Halffloat128Vector)v, (Halffloat128Mask)m,
                              f);  // specialize
    }

    // Ternary operator

    @ForceInline
    final @Override
    Halffloat128Vector tOp(Vector<Halffloat> v1, Vector<Halffloat> v2, FTriOp f) {
        return (Halffloat128Vector)
            super.tOpTemplate((Halffloat128Vector)v1, (Halffloat128Vector)v2,
                              f);  // specialize
    }

    @ForceInline
    final @Override
    Halffloat128Vector tOp(Vector<Halffloat> v1, Vector<Halffloat> v2,
                     VectorMask<Halffloat> m, FTriOp f) {
        return (Halffloat128Vector)
            super.tOpTemplate((Halffloat128Vector)v1, (Halffloat128Vector)v2,
                              (Halffloat128Mask)m, f);  // specialize
    }

    @ForceInline
    final @Override
    short rOp(short v, VectorMask<Halffloat> m, FBinOp f) {
        return super.rOpTemplate(v, m, f);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> convertShape(VectorOperators.Conversion<Halffloat,F> conv,
                           VectorSpecies<F> rsp, int part) {
        return super.convertShapeTemplate(conv, rsp, part);  // specialize
    }

    @Override
    @ForceInline
    public final <F>
    Vector<F> reinterpretShape(VectorSpecies<F> toSpecies, int part) {
        return super.reinterpretShapeTemplate(toSpecies, part);  // specialize
    }

    // Specialized algebraic operations:

    // The following definition forces a specialized version of this
    // crucial method into the v-table of this class.  A call to add()
    // will inline to a call to lanewise(ADD,), at which point the JIT
    // intrinsic will have the opcode of ADD, plus all the metadata
    // for this particular class, enabling it to generate precise
    // code.
    //
    // There is probably no benefit to the JIT to specialize the
    // masked or broadcast versions of the lanewise method.

    @Override
    @ForceInline
    public Halffloat128Vector lanewise(Unary op) {
        return (Halffloat128Vector) super.lanewiseTemplate(op);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector lanewise(Unary op, VectorMask<Halffloat> m) {
        return (Halffloat128Vector) super.lanewiseTemplate(op, Halffloat128Mask.class, (Halffloat128Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector lanewise(Binary op, Vector<Halffloat> v) {
        return (Halffloat128Vector) super.lanewiseTemplate(op, v);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector lanewise(Binary op, Vector<Halffloat> v, VectorMask<Halffloat> m) {
        return (Halffloat128Vector) super.lanewiseTemplate(op, Halffloat128Mask.class, v, (Halffloat128Mask) m);  // specialize
    }


    /*package-private*/
    @Override
    @ForceInline
    public final
    Halffloat128Vector
    lanewise(Ternary op, Vector<Halffloat> v1, Vector<Halffloat> v2) {
        return (Halffloat128Vector) super.lanewiseTemplate(op, v1, v2);  // specialize
    }

    @Override
    @ForceInline
    public final
    Halffloat128Vector
    lanewise(Ternary op, Vector<Halffloat> v1, Vector<Halffloat> v2, VectorMask<Halffloat> m) {
        return (Halffloat128Vector) super.lanewiseTemplate(op, Halffloat128Mask.class, v1, v2, (Halffloat128Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public final
    Halffloat128Vector addIndex(int scale) {
        return (Halffloat128Vector) super.addIndexTemplate(scale);  // specialize
    }

    // Type specific horizontal reductions

    @Override
    @ForceInline
    public final short reduceLanes(VectorOperators.Associative op) {
        return super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final short reduceLanes(VectorOperators.Associative op,
                                    VectorMask<Halffloat> m) {
        return super.reduceLanesTemplate(op, Halffloat128Mask.class, (Halffloat128Mask) m);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op) {
        return (long) super.reduceLanesTemplate(op);  // specialized
    }

    @Override
    @ForceInline
    public final long reduceLanesToLong(VectorOperators.Associative op,
                                        VectorMask<Halffloat> m) {
        return (long) super.reduceLanesTemplate(op, Halffloat128Mask.class, (Halffloat128Mask) m);  // specialized
    }

    @ForceInline
    public VectorShuffle<Halffloat> toShuffle() {
        return super.toShuffleTemplate(Halffloat128Shuffle.class); // specialize
    }

    // Specialized unary testing

    @Override
    @ForceInline
    public final Halffloat128Mask test(Test op) {
        return super.testTemplate(Halffloat128Mask.class, op);  // specialize
    }

    @Override
    @ForceInline
    public final Halffloat128Mask test(Test op, VectorMask<Halffloat> m) {
        return super.testTemplate(Halffloat128Mask.class, op, (Halffloat128Mask) m);  // specialize
    }

    // Specialized comparisons

    @Override
    @ForceInline
    public final Halffloat128Mask compare(Comparison op, Vector<Halffloat> v) {
        return super.compareTemplate(Halffloat128Mask.class, op, v);  // specialize
    }

    @Override
    @ForceInline
    public final Halffloat128Mask compare(Comparison op, short s) {
        return super.compareTemplate(Halffloat128Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public final Halffloat128Mask compare(Comparison op, long s) {
        return super.compareTemplate(Halffloat128Mask.class, op, s);  // specialize
    }

    @Override
    @ForceInline
    public final Halffloat128Mask compare(Comparison op, Vector<Halffloat> v, VectorMask<Halffloat> m) {
        return super.compareTemplate(Halffloat128Mask.class, op, v, (Halffloat128Mask) m);
    }


    @Override
    @ForceInline
    public Halffloat128Vector blend(Vector<Halffloat> v, VectorMask<Halffloat> m) {
        return (Halffloat128Vector)
            super.blendTemplate(Halffloat128Mask.class,
                                (Halffloat128Vector) v,
                                (Halffloat128Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector slice(int origin, Vector<Halffloat> v) {
        return (Halffloat128Vector) super.sliceTemplate(origin, v);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector slice(int origin) {
        return (Halffloat128Vector) super.sliceTemplate(origin);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector unslice(int origin, Vector<Halffloat> w, int part) {
        return (Halffloat128Vector) super.unsliceTemplate(origin, w, part);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector unslice(int origin, Vector<Halffloat> w, int part, VectorMask<Halffloat> m) {
        return (Halffloat128Vector)
            super.unsliceTemplate(Halffloat128Mask.class,
                                  origin, w, part,
                                  (Halffloat128Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector unslice(int origin) {
        return (Halffloat128Vector) super.unsliceTemplate(origin);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector rearrange(VectorShuffle<Halffloat> s) {
        return (Halffloat128Vector)
            super.rearrangeTemplate(Halffloat128Shuffle.class,
                                    (Halffloat128Shuffle) s);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector rearrange(VectorShuffle<Halffloat> shuffle,
                                  VectorMask<Halffloat> m) {
        return (Halffloat128Vector)
            super.rearrangeTemplate(Halffloat128Shuffle.class,
                                    Halffloat128Mask.class,
                                    (Halffloat128Shuffle) shuffle,
                                    (Halffloat128Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector rearrange(VectorShuffle<Halffloat> s,
                                  Vector<Halffloat> v) {
        return (Halffloat128Vector)
            super.rearrangeTemplate(Halffloat128Shuffle.class,
                                    (Halffloat128Shuffle) s,
                                    (Halffloat128Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector compress(VectorMask<Halffloat> m) {
        return (Halffloat128Vector)
            super.compressTemplate(Halffloat128Mask.class,
                                   (Halffloat128Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector expand(VectorMask<Halffloat> m) {
        return (Halffloat128Vector)
            super.expandTemplate(Halffloat128Mask.class,
                                   (Halffloat128Mask) m);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector selectFrom(Vector<Halffloat> v) {
        return (Halffloat128Vector)
            super.selectFromTemplate((Halffloat128Vector) v);  // specialize
    }

    @Override
    @ForceInline
    public Halffloat128Vector selectFrom(Vector<Halffloat> v,
                                   VectorMask<Halffloat> m) {
        return (Halffloat128Vector)
            super.selectFromTemplate((Halffloat128Vector) v,
                                     (Halffloat128Mask) m);  // specialize
    }


    @ForceInline
    @Override
    public short lane(int i) {
        short bits;
        switch(i) {
            case 0: bits = laneHelper(0); break;
            case 1: bits = laneHelper(1); break;
            case 2: bits = laneHelper(2); break;
            case 3: bits = laneHelper(3); break;
            case 4: bits = laneHelper(4); break;
            case 5: bits = laneHelper(5); break;
            case 6: bits = laneHelper(6); break;
            case 7: bits = laneHelper(7); break;
            default: throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
        return Halffloat.shortBitsToHalffloat(bits);
    }

    public short laneHelper(int i) {
        return (short) VectorSupport.extract(
                     VCLASS, ETYPE, VLENGTH,
                     this, i,
                     (vec, ix) -> {
                     short[] vecarr = vec.vec();
                     return (long)Halffloat.shortToShortBits(vecarr[ix]);
                     });
    }

    @ForceInline
    @Override
    public Halffloat128Vector withLane(int i, short e) {
        switch(i) {
            case 0: return withLaneHelper(0, e);
            case 1: return withLaneHelper(1, e);
            case 2: return withLaneHelper(2, e);
            case 3: return withLaneHelper(3, e);
            case 4: return withLaneHelper(4, e);
            case 5: return withLaneHelper(5, e);
            case 6: return withLaneHelper(6, e);
            case 7: return withLaneHelper(7, e);
            default: throw new IllegalArgumentException("Index " + i + " must be zero or positive, and less than " + VLENGTH);
        }
    }

    public Halffloat128Vector withLaneHelper(int i, short e) {
        return VectorSupport.insert(
                                VCLASS, ETYPE, VLENGTH,
                                this, i, (long)Halffloat.shortToShortBits(e),
                                (v, ix, bits) -> {
                                    short[] res = v.vec().clone();
                                    res[ix] = Halffloat.shortBitsToHalffloat((short)bits);
                                    return v.vectorFactory(res);
                                });
    }

    // Mask

    static final class Halffloat128Mask extends AbstractMask<Halffloat> {
        static final int VLENGTH = VSPECIES.laneCount();    // used by the JVM
        static final Class<Halffloat> ETYPE = Halffloat.class; // used by the JVM

        Halffloat128Mask(boolean[] bits) {
            this(bits, 0);
        }

        Halffloat128Mask(boolean[] bits, int offset) {
            super(prepare(bits, offset));
        }

        Halffloat128Mask(boolean val) {
            super(prepare(val));
        }

        private static boolean[] prepare(boolean[] bits, int offset) {
            boolean[] newBits = new boolean[VSPECIES.laneCount()];
            for (int i = 0; i < newBits.length; i++) {
                newBits[i] = bits[offset + i];
            }
            return newBits;
        }

        private static boolean[] prepare(boolean val) {
            boolean[] bits = new boolean[VSPECIES.laneCount()];
            Arrays.fill(bits, val);
            return bits;
        }

        @ForceInline
        final @Override
        public HalffloatSpecies vspecies() {
            // ISSUE:  This should probably be a @Stable
            // field inside AbstractMask, rather than
            // a megamorphic method.
            return VSPECIES;
        }

        @ForceInline
        boolean[] getBits() {
            return (boolean[])getPayload();
        }

        @Override
        Halffloat128Mask uOp(MUnOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i]);
            }
            return new Halffloat128Mask(res);
        }

        @Override
        Halffloat128Mask bOp(VectorMask<Halffloat> m, MBinOp f) {
            boolean[] res = new boolean[vspecies().laneCount()];
            boolean[] bits = getBits();
            boolean[] mbits = ((Halffloat128Mask)m).getBits();
            for (int i = 0; i < res.length; i++) {
                res[i] = f.apply(i, bits[i], mbits[i]);
            }
            return new Halffloat128Mask(res);
        }

        @ForceInline
        @Override
        public final
        Halffloat128Vector toVector() {
            return (Halffloat128Vector) super.toVectorTemplate();  // specialize
        }

        /**
         * Helper function for lane-wise mask conversions.
         * This function kicks in after intrinsic failure.
         */
        @ForceInline
        private final <E>
        VectorMask<E> defaultMaskCast(AbstractSpecies<E> dsp) {
            if (length() != dsp.laneCount())
                throw new IllegalArgumentException("VectorMask length and species length differ");
            boolean[] maskArray = toArray();
            return  dsp.maskFactory(maskArray).check(dsp);
        }

        @Override
        @ForceInline
        public <E> VectorMask<E> cast(VectorSpecies<E> dsp) {
            AbstractSpecies<E> species = (AbstractSpecies<E>) dsp;
            if (length() != species.laneCount())
                throw new IllegalArgumentException("VectorMask length and species length differ");

            return VectorSupport.convert(VectorSupport.VECTOR_OP_CAST,
                this.getClass(), ETYPE, VLENGTH,
                species.maskType(), species.elementType(), VLENGTH,
                this, species,
                (m, s) -> s.maskFactory(m.toArray()).check(s));
        }

        @Override
        @ForceInline
        public Halffloat128Mask eq(VectorMask<Halffloat> mask) {
            Objects.requireNonNull(mask);
            Halffloat128Mask m = (Halffloat128Mask)mask;
            return xor(m.not());
        }

        // Unary operations

        @Override
        @ForceInline
        public Halffloat128Mask not() {
            return xor(maskAll(true));
        }

        @Override
        @ForceInline
        public Halffloat128Mask compress() {
            return (Halffloat128Mask)VectorSupport.comExpOp(VectorSupport.VECTOR_OP_MASK_COMPRESS,
                Halffloat128Vector.class, Halffloat128Mask.class, ETYPE, VLENGTH, null, this,
                (v1, m1) -> VSPECIES.iota().compare(VectorOperators.LT, m1.trueCount()));
        }


        // Binary operations

        @Override
        @ForceInline
        public Halffloat128Mask and(VectorMask<Halffloat> mask) {
            Objects.requireNonNull(mask);
            Halffloat128Mask m = (Halffloat128Mask)mask;
            return VectorSupport.binaryOp(VECTOR_OP_AND, Halffloat128Mask.class, null, Halffloat.class, VLENGTH,
                                          this, m, null,
                                          (m1, m2, vm) -> m1.bOp(m2, (i, a, b) -> a & b));
        }

        @Override
        @ForceInline
        public Halffloat128Mask or(VectorMask<Halffloat> mask) {
            Objects.requireNonNull(mask);
            Halffloat128Mask m = (Halffloat128Mask)mask;
            return VectorSupport.binaryOp(VECTOR_OP_OR, Halffloat128Mask.class, null, Halffloat.class, VLENGTH,
                                          this, m, null,
                                          (m1, m2, vm) -> m1.bOp(m2, (i, a, b) -> a | b));
        }

        @ForceInline
        /* package-private */
        Halffloat128Mask xor(VectorMask<Halffloat> mask) {
            Objects.requireNonNull(mask);
            Halffloat128Mask m = (Halffloat128Mask)mask;
            return VectorSupport.binaryOp(VECTOR_OP_XOR, Halffloat128Mask.class, null, Halffloat.class, VLENGTH,
                                          this, m, null,
                                          (m1, m2, vm) -> m1.bOp(m2, (i, a, b) -> a ^ b));
        }

        // Mask Query operations

        @Override
        @ForceInline
        public int trueCount() {
            return (int) VectorSupport.maskReductionCoerced(VECTOR_OP_MASK_TRUECOUNT, Halffloat128Mask.class, Halffloat.class, VLENGTH, this,
                                                      (m) -> trueCountHelper(m.getBits()));
        }

        @Override
        @ForceInline
        public int firstTrue() {
            return (int) VectorSupport.maskReductionCoerced(VECTOR_OP_MASK_FIRSTTRUE, Halffloat128Mask.class, Halffloat.class, VLENGTH, this,
                                                      (m) -> firstTrueHelper(m.getBits()));
        }

        @Override
        @ForceInline
        public int lastTrue() {
            return (int) VectorSupport.maskReductionCoerced(VECTOR_OP_MASK_LASTTRUE, Halffloat128Mask.class, Halffloat.class, VLENGTH, this,
                                                      (m) -> lastTrueHelper(m.getBits()));
        }

        @Override
        @ForceInline
        public long toLong() {
            if (length() > Long.SIZE) {
                throw new UnsupportedOperationException("too many lanes for one long");
            }
            return VectorSupport.maskReductionCoerced(VECTOR_OP_MASK_TOLONG, Halffloat128Mask.class, Halffloat.class, VLENGTH, this,
                                                      (m) -> toLongHelper(m.getBits()));
        }

        // Reductions

        @Override
        @ForceInline
        public boolean anyTrue() {
            return VectorSupport.test(BT_ne, Halffloat128Mask.class, Halffloat.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> anyTrueHelper(((Halffloat128Mask)m).getBits()));
        }

        @Override
        @ForceInline
        public boolean allTrue() {
            return VectorSupport.test(BT_overflow, Halffloat128Mask.class, Halffloat.class, VLENGTH,
                                         this, vspecies().maskAll(true),
                                         (m, __) -> allTrueHelper(((Halffloat128Mask)m).getBits()));
        }

        @ForceInline
        /*package-private*/
        static Halffloat128Mask maskAll(boolean bit) {
            return VectorSupport.fromBitsCoerced(Halffloat128Mask.class, Halffloat.class, VLENGTH,
                                                 (bit ? -1 : 0), MODE_BROADCAST, null,
                                                 (v, __) -> (v != 0 ? TRUE_MASK : FALSE_MASK));
        }
        private static final Halffloat128Mask  TRUE_MASK = new Halffloat128Mask(true);
        private static final Halffloat128Mask FALSE_MASK = new Halffloat128Mask(false);

    }

    // Shuffle

    static final class Halffloat128Shuffle extends AbstractShuffle<Halffloat> {
        static final int VLENGTH = VSPECIES.laneCount();    // used by the JVM
        static final Class<Halffloat> ETYPE = Halffloat.class; // used by the JVM

        Halffloat128Shuffle(byte[] reorder) {
            super(VLENGTH, reorder);
        }

        public Halffloat128Shuffle(int[] reorder) {
            super(VLENGTH, reorder);
        }

        public Halffloat128Shuffle(int[] reorder, int i) {
            super(VLENGTH, reorder, i);
        }

        public Halffloat128Shuffle(IntUnaryOperator fn) {
            super(VLENGTH, fn);
        }

        @Override
        public HalffloatSpecies vspecies() {
            return VSPECIES;
        }

        static {
            // There must be enough bits in the shuffle lanes to encode
            // VLENGTH valid indexes and VLENGTH exceptional ones.
            assert(VLENGTH < Byte.MAX_VALUE);
            assert(Byte.MIN_VALUE <= -VLENGTH);
        }
        static final Halffloat128Shuffle IOTA = new Halffloat128Shuffle(IDENTITY);

        @Override
        @ForceInline
        public Halffloat128Vector toVector() {
            return VectorSupport.shuffleToVector(VCLASS, ETYPE, Halffloat128Shuffle.class, this, VLENGTH,
                                                    (s) -> ((Halffloat128Vector)(((AbstractShuffle<Halffloat>)(s)).toVectorTemplate())));
        }

        @Override
        @ForceInline
        public <F> VectorShuffle<F> cast(VectorSpecies<F> s) {
            AbstractSpecies<F> species = (AbstractSpecies<F>) s;
            if (length() != species.laneCount())
                throw new IllegalArgumentException("VectorShuffle length and species length differ");
            int[] shuffleArray = toArray();
            return s.shuffleFromArray(shuffleArray, 0).check(s);
        }

        @ForceInline
        @Override
        public Halffloat128Shuffle rearrange(VectorShuffle<Halffloat> shuffle) {
            Halffloat128Shuffle s = (Halffloat128Shuffle) shuffle;
            byte[] reorder1 = reorder();
            byte[] reorder2 = s.reorder();
            byte[] r = new byte[reorder1.length];
            for (int i = 0; i < reorder1.length; i++) {
                int ssi = reorder2[i];
                r[i] = reorder1[ssi];  // throws on exceptional index
            }
            return new Halffloat128Shuffle(r);
        }
    }

    // ================================================

    // Specialized low-level memory operations.

    @ForceInline
    @Override
    final
    HalffloatVector fromArray0(short[] a, int offset) {
        return super.fromArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    HalffloatVector fromArray0(short[] a, int offset, VectorMask<Halffloat> m) {
        return super.fromArray0Template(Halffloat128Mask.class, a, offset, (Halffloat128Mask) m);  // specialize
    }


    @ForceInline
    @Override
    final
    HalffloatVector fromCharArray0(char[] a, int offset) {
        return super.fromCharArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    HalffloatVector fromCharArray0(char[] a, int offset, VectorMask<Halffloat> m) {
        return super.fromCharArray0Template(Halffloat128Mask.class, a, offset, (Halffloat128Mask) m);  // specialize
    }


    @ForceInline
    @Override
    final
    HalffloatVector fromMemorySegment0(MemorySegment ms, long offset) {
        return super.fromMemorySegment0Template(ms, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    HalffloatVector fromMemorySegment0(MemorySegment ms, long offset, VectorMask<Halffloat> m) {
        return super.fromMemorySegment0Template(Halffloat128Mask.class, ms, offset, (Halffloat128Mask) m);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoArray0(short[] a, int offset) {
        super.intoArray0Template(a, offset);  // specialize
    }

    @ForceInline
    @Override
    final
    void intoArray0(short[] a, int offset, VectorMask<Halffloat> m) {
        super.intoArray0Template(Halffloat128Mask.class, a, offset, (Halffloat128Mask) m);
    }



    @ForceInline
    @Override
    final
    void intoMemorySegment0(MemorySegment ms, long offset, VectorMask<Halffloat> m) {
        super.intoMemorySegment0Template(Halffloat128Mask.class, ms, offset, (Halffloat128Mask) m);
    }

    @ForceInline
    @Override
    final
    void intoCharArray0(char[] a, int offset, VectorMask<Halffloat> m) {
        super.intoCharArray0Template(Halffloat128Mask.class, a, offset, (Halffloat128Mask) m);
    }

    // End of specialized low-level memory operations.

    // ================================================

}
